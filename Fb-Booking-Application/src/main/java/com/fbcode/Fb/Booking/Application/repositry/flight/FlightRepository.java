package com.fbcode.Fb.Booking.Application.repositry.flight;

import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    Optional<FlightEntity> findByFlightNumber(String flightNumber);
    List<FlightEntity> findByCarrier(String carrier);
    List<FlightEntity> findByAircraftType(String aircraftType);
    List<FlightEntity> findByTotalSeatsGreaterThan(Integer seats);
    List<FlightEntity> findByCarrierAndAircraftType(String carrier, String aircraftType);

}


