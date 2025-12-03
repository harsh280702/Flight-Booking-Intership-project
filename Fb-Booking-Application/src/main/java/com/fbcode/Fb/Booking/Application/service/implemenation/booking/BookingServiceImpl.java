package com.fbcode.Fb.Booking.Application.service.implemenation.booking;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.repositry.booking.BookingRepositry;
import com.fbcode.Fb.Booking.Application.repositry.flight.FlightScheduleRepository;
import com.fbcode.Fb.Booking.Application.repositry.flight.SeatInventoryRepository;
import com.fbcode.Fb.Booking.Application.repositry.user.UserRepository;
import com.fbcode.Fb.Booking.Application.service.InterFace.booking.IBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBooking {

    private final BookingRepositry bookingRepository;
    private final SeatInventoryRepository seatInventoryRepository;
    private final FlightScheduleRepository flightScheduleRepository;
    private final UserRepository userRepository;


    @Override
    public Mono<UserResponseDTO> bookTIcker(BookingEntity bookingEntity) {
        return null;
    }

    @Override
    public Mono<UserResponseDTO> cancelBooking(String flightId) {
        return null;
    }

}
