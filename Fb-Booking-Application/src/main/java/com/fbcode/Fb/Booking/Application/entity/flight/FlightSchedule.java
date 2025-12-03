package com.fbcode.Fb.Booking.Application.entity.flight;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.dto.flight.SeatInventoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="flight_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSchedule extends FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many schedules can belong to one flight
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @OneToOne
    @JoinColumn(name = "FLightNumber", nullable = false)
    private  FlightEntity  flightNumber;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private AirportEntity origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private AirportEntity destination;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    private String status;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<SeatInventory> seats;

}
