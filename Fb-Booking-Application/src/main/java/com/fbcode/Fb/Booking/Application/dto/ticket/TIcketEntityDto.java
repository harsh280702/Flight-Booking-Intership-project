package com.fbcode.Fb.Booking.Application.dto.ticket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketStatus;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TIcketEntityDto {
    private Long id;
    private String ticketNumber;
    private String pnr;
    private String seatNumber;
    private TicketStatus status = TicketStatus.CANCELLED; // BOOKED, CANCELLED
    private Double fare;
    private String passengerName;
    private Integer passengerAge;
    private String passengerGender;
    private BookingEntity booking;
    private FlightEntity flight;
    private UserEntity user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
