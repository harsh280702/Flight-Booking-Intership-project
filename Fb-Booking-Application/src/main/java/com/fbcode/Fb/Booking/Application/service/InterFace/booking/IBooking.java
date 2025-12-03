package com.fbcode.Fb.Booking.Application.service.InterFace.booking;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import reactor.core.publisher.Mono;

public interface IBooking {


    Mono<UserResponseDTO>  bookTIcker(BookingEntity bookingEntity);
    Mono<UserResponseDTO> cancelBooking(String flightId);
}
