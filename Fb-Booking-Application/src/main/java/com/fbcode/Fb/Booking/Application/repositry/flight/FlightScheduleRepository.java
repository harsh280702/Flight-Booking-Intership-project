package com.fbcode.Fb.Booking.Application.repositry.flight;

import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

    @Query("SELECT * FROM flight_schedule fs " +
                  "WHERE fs.origin_airport_id = :originId " +
                  "AND fs.destination_airport_id = :destId " +
                  "AND DATE(fs.departure_time) = :date")
    Optional<List<FlightSchedule>> findByRouteAndDate(Long originId, Long destId, LocalDate date);

    @Override
    Optional<FlightSchedule> findById(Long aLong);

    Optional<FlightSchedule> findByFlightNumber(String flightNumber);

    @Query("SELECT fs FROM FlightSchedule fs WHERE fs.flight.flightNumber = :flightNumber")
    List<FlightSchedule> findAllSchedulesByFlightNumber(String flightNumber);

    @Query("SELECT fs.status FROM FlightSchedule fs WHERE fs.flight.flightNumber = :flightNumber")
    String findStatusByFlightNumber(@Param("flightNumber") String flightNumber);
}
