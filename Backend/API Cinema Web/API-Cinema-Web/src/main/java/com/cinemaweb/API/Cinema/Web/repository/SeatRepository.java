package com.cinemaweb.API.Cinema.Web.repository;

import com.cinemaweb.API.Cinema.Web.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {
}
