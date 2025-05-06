package com.cinemaweb.API.Cinema.Web.dto.request;


import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8)
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    int gender;
    String phoneNumber;
    String email;
    String avatar;
    Set<String> roles;
}
