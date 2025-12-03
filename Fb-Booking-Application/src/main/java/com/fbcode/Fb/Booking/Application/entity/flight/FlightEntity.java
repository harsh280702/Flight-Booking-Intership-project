package com.fbcode.Fb.Booking.Application.entity.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;

    private String carrier;
    private String aircraftType;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;


    // Relationship: One Flight → Many Schedules
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<FlightSchedule> schedules = new ArrayList<>();
}
