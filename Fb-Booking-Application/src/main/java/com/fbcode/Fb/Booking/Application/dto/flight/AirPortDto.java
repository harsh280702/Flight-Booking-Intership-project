package com.fbcode.Fb.Booking.Application.dto.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirPortDto {

    private Long id;
    private String code;
    private String name;
    private String city;
    private String state;
    private String country;
    private List<FlightSchedule> originSchedules = new ArrayList<>();
    private List<FlightSchedule> destinationSchedules = new ArrayList<>();



}


