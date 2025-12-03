package com.fbcode.Fb.Booking.Application.service.InterFace.flight;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IFlightServices {


    //------------------------------------------------
    // these functioon is for  use in the Flight Entity
    //-------------------------------------------------
    Mono<UserResponseDTO> addFlight(FlightEntity flightEntity);
    Mono<UserResponseDTO> updateFlight(String  flightNumber,FlightEntity flightEntity);
    Mono<UserResponseDTO> deleteFlight(String  flightNumber);

    Mono<UserResponseDTO> findFlightByFlightNumber(String flightNumber);
    Flux<UserResponseDTO> findLargeCapacityFlights(int minSeats);
    Flux<UserResponseDTO> searchFlightsByCarrier( String carrier);
    //------------------------------------------------
    // these functioon is for  use in the Flight Entity
    //-------------------------------------------------

    Mono<UserResponseDTO> addFlightSchedule( FlightSchedule flightSchedule);
    Mono<UserResponseDTO> updateFlightSchedule(Long id , FlightSchedule flightSchedule);
    Mono<UserResponseDTO> findFlightScheduleByFlightNumber(String flightNumber);
    Flux<UserResponseDTO> searchFlightScheduleByCarrier(Long originId, Long destId, LocalDate date);
    Mono<String> findStatusOFFlightbyFlightNumber(String flightNumber);




    //----------------------------------------------------
    // these function use  is for  use the Flight Schedule
    //----------------------------------------------------
    Flux <UserResponseDTO> getAvailableSeats(Long id);
    Mono<Long> countAvailableSeats(Long id);
    Flux<UserResponseDTO> getSeatInventory(Long id);



    //------------------------------------------------------
    //these Function use is  for the use the  Airport Entity
    //------------------------------------------------------
    Flux<UserResponseDTO> findAirportByCity(String city);

}



