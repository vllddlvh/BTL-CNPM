package com.cinemaweb.API.Cinema.Web.configuration;

import com.cinemaweb.API.Cinema.Web.entity.User;
import com.cinemaweb.API.Cinema.Web.enums.Role;
import com.cinemaweb.API.Cinema.Web.repository.RoleRepository;
import com.cinemaweb.API.Cinema.Web.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;


    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                var roles = new HashSet<>(roleRepository.findAllById(List.of(Role.ADMIN.name())));
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .gender(1)
                        .roles(roles)
                        .phoneNumber("000000000")
                        .email("admin@cinemaweb.com")
                        .firstName("admin")
                        .lastName("admin")
                        .dateOfBirth(LocalDate.parse("2005-09-06"))
                        .build();
                userRepository.save(user);
                log.warn("admin account has been created with default password: admin!");
            }
        };
    }


}
