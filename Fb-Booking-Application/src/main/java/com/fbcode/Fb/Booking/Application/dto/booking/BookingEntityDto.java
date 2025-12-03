package com.fbcode.Fb.Booking.Application.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingStatus;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingEntityDto {

    private Long bookingId;
    // Unique Booking Reference (PNR)
    private String pnr;
    // User mapped
    private UserEntity user;
    // Flight mapped
    private FlightEntity flight;
    // Number of seats booked
    private int seats;
    // Total price
    private double totalAmount;
    // Booking Status (BOOKED, CANCELLED)
    private BookingStatus status;
    // Date + Time of booking
    private LocalDateTime bookingDateTime;
    // Passenger Details (optional)
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
}
