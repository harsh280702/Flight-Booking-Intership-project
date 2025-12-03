package com.fbcode.Fb.Booking.Application.entity.ticket;

import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique Ticket Number
    @Column(nullable = false, unique = true)
    private String ticketNumber;

    // PNR (same for all tickets under same booking)
    @Column(nullable = false)
    private String pnr;

    // Seat number (optional)
    private String seatNumber;

    // Ticket Status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.CANCELLED; // BOOKED, CANCELLED

    // Fare Per Ticket
    @Column(nullable = false)
    private Double fare;

    // Passenger name (if each ticket belongs to specific passenger)
    @Column(nullable = false)
    private String passengerName;

    private Integer passengerAge;

    private String passengerGender;

    // Relation: Ticket belongs to a booking
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    // Relation: Ticket belongs to a flight
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    // Relation: Ticket belongs to a user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Timestamp fields
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
