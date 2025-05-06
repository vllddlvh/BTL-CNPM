package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.*;
import com.cinemaweb.API.Cinema.Web.dto.response.ApiResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.AuthenticationResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.IntrospectResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.PasswordResetResponse;
import com.cinemaweb.API.Cinema.Web.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.warn("dang login");
        return ApiResponse.<AuthenticationResponse>builder()
                .body(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .body(authenticationService.introspect(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh-Token")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .body(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/forget-password")
    public ApiResponse<String> getPasswordOTP(@RequestBody PasswordOtpRequest request) {
        return ApiResponse.<String>builder()
                .message(authenticationService.getPasswordToken(request.getEmail()))
                .build();
    }

    @PostMapping("/reset-password/{otp}")
    public ApiResponse<PasswordResetResponse> resetPassword(@RequestBody @Valid PasswordResetRequest request, @PathVariable("otp") String OTP) {
        return ApiResponse.<PasswordResetResponse>builder()
                .body(authenticationService.resetPassword(request, OTP))
                .build();
    }

}
