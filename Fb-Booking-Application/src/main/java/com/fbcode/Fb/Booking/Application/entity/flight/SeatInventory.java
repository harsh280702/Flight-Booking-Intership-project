package com.fbcode.Fb.Booking.Application.entity.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name ="seat_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId; // FK -> flight_schedule.id
    private String seatNumber; // "12A"
    private String seatClass; // ECONOMY, PREMIUM, BUSINESS
    private Boolean isReserved;
    private BigDecimal priceModifier;
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private FlightSchedule schedule;
}
