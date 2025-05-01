package com.cinemaweb.API.Cinema.Web.service;


import com.cinemaweb.API.Cinema.Web.dto.request.*;
import com.cinemaweb.API.Cinema.Web.dto.response.AuthenticationResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.IntrospectResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.PasswordResetResponse;
import com.cinemaweb.API.Cinema.Web.entity.InvalidatedToken;
import com.cinemaweb.API.Cinema.Web.entity.PasswordOTP;
import com.cinemaweb.API.Cinema.Web.entity.User;
import com.cinemaweb.API.Cinema.Web.exception.AppException;
import com.cinemaweb.API.Cinema.Web.exception.ErrorCode;
import com.cinemaweb.API.Cinema.Web.repository.InvalidatedTokenRepository;
import com.cinemaweb.API.Cinema.Web.repository.PasswordOtpRepository;
import com.cinemaweb.API.Cinema.Web.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {

    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    EmailService emailService;
    PasswordOtpRepository passwordOtpRepository;
    UserService userService;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        SignedJWT signedJWT = verifyToken(request.getToken());
        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
        String tokenID = claimsSet.getJWTID();
        Date expiryTime = claimsSet.getExpirationTime();

        invalidatedTokenRepository.save(new InvalidatedToken(tokenID, expiryTime));

    }


    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public String getPasswordToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        if (passwordOtpRepository.countOtp(user.getID()) > 0) {
            throw new AppException(ErrorCode.WAIT_OTP);
        }


        passwordOtpRepository.invalidateOtp(user.getID());
        passwordOtpRepository.flush();

        PasswordOTP passwordOTP = PasswordOTP.builder()
                .OTP(UUID.randomUUID().toString())
                .user(user)
                .expiryTime(new Date(Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli()))
                .valid(true)
                .build();

        passwordOtpRepository.save(passwordOTP);
        emailService.sendResetPasswordOtp(user, passwordOTP);

        return "Please check your email to get OTP used to reset your password!";

        // sendMail có thể ném ra MailException hãy xử lý trong globalExceptionHandler
    }


    @Transactional(rollbackFor = Exception.class)
    public PasswordResetResponse resetPassword(PasswordResetRequest request, String OTP) {
        PasswordOTP passwordOTP = verifyOTP(OTP);

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.CONFIRM_PASSWORD_FAIL);
        }

        User user = userService.resetPassword(passwordOTP.getUser(), request.getNewPassword());
        passwordOTP.setValid(false);
        passwordOtpRepository.save(passwordOTP);

        return PasswordResetResponse.builder()
                .token(generateToken(user))
                .build();
    }


    private PasswordOTP verifyOTP(String OTP) {
        PasswordOTP passwordOTP = passwordOtpRepository.findById(OTP)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));

        if (passwordOTP.getExpiryTime().after(new Date()) && passwordOTP.isValid()) {
            return passwordOTP;
        }

        throw new AppException(ErrorCode.INVALID_OTP);
    }
    

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(request.getToken());
        String tokenID = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = new InvalidatedToken(tokenID, expiryTime);

        invalidatedTokenRepository.save(invalidatedToken);
        String username = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        String newToken = generateToken(user);
        return AuthenticationResponse.builder()
                .token(newToken)
                .authenticated(true)
                .build();
    }


    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime =signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(jwsVerifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }


    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet =  new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("API-Cinema-Web")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create Token!!", e);
            throw new RuntimeException(e);
        }

    }



    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!role.getPermissions().isEmpty())
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));

            });
        }
        return stringJoiner.toString();
    }


}
