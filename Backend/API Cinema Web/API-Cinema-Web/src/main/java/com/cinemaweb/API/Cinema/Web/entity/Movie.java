package com.cinemaweb.API.Cinema.Web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    int movieId;

    String movieName;
    String moviePoster;
    String movieGenre;
    int movieLength;  //this time watch film
    String movieDescription;
    int movieReview;  //its star 1* 2* 3* ...
}
