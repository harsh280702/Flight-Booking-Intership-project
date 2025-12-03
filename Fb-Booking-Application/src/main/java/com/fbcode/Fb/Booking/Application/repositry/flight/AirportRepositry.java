package com.fbcode.Fb.Booking.Application.repositry.flight;

import com.fbcode.Fb.Booking.Application.entity.flight.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepositry  extends JpaRepository <AirportEntity,Long> {

    Optional<List<AirportEntity>> findAirportByCity(String city);
}
