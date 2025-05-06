package com.cinemaweb.API.Cinema.Web.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class ApiResponse<T> {
    int code;
    String message;
    T body;
}
