package com.fbcode.Fb.Booking.Application.entity.booking;

import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    // Unique Booking Reference (PNR)
    @Column(nullable = false, unique = true)
    private String pnr;

    // User mapped
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Flight mapped
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightSchedule flight;


    // Number of seats booked
    @Column(nullable = false)
    private int seats;
    private String seatClass;

    // Total price
    @Column(nullable = false)
    private double totalAmount;

    // Booking Status (BOOKED, CANCELLED)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    // Date + Time of booking
    private LocalDateTime bookingDateTime;

    // Passenger Details (optional)
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;


}
