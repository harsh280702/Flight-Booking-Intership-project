package com.fbcode.Fb.Booking.Application.dto.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatInventoryDTO {
    private Long id;

    private Long scheduleId; // FK -> flight_schedule.id
    private String seatNumber; // "12A"
    private String seatClass; // ECONOMY, PREMIUM, BUSINESS
    private Boolean isReserved;
    private BigDecimal priceModifier;
    private FlightSchedule schedule;

}
