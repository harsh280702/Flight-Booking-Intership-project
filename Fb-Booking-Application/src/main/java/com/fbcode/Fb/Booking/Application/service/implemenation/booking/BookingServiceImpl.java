package com.fbcode.Fb.Booking.Application.service.implemenation.booking;

import com.fbcode.Fb.Booking.Application.repositry.flight.FlightRepository;
import com.fbcode.Fb.Booking.Application.repositry.user.UserRepositry;
import com.fbcode.Fb.Booking.Application.service.InterFace.booking.IBooking;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements IBooking {

    private FlightRepository flightRepository;
    private UserRepositry userRepositry;

}
