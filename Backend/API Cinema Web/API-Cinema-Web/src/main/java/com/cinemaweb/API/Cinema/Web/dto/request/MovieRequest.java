package com.cinemaweb.API.Cinema.Web.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
/**
 * This dto is creation dto and update dto
 */
public class MovieRequest {
    @Size(max = 30, message = "Min length film name is  30 character")
    String movieName;

    String moviePoster;
    String movieGenre;
    int movieLength;  //this time watch film
    String movieDescription;
    int movieReview;  //its star 1* 2* 3* ...
}
