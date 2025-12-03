package com.fbcode.Fb.Booking.Application.dto.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightEntityDto {

    private Long id;
    private String flightNumber;
    private String carrier;
    private String aircraftType;
    private Integer totalSeats;
    private List<FlightSchedule> schedules = new ArrayList<>();


}
