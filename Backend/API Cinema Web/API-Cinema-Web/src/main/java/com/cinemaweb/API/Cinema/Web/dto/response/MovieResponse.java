package com.cinemaweb.API.Cinema.Web.dto.response;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    @Size(min = 30, message = "Min length film name is  30 character")
    String movieName;

    String moviePoster;
    String movieGenre;
    int movieLength;  //this time watch film
    String movieDescription;
    int movieReview;  //its star 1* 2* 3* ...
}
