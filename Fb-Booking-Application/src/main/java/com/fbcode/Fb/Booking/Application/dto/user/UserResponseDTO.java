package com.fbcode.Fb.Booking.Application.dto.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.dto.booking.BookingEntityDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightEntityDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightScheduleDto;
import com.fbcode.Fb.Booking.Application.dto.flight.SeatInventoryDTO;
import com.fbcode.Fb.Booking.Application.dto.ticket.TIcketEntityDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {


    private  int statusCode;


    private  String message;

    private  UserEntityDto  user;

    private List<UserEntityDto> userEntityDtoList;
    private TIcketEntityDto  tIcketEntityDto;
    private List<TIcketEntityDto>  ticketEntityList;
    private FlightEntityDto flightEntityDto;
    private List<FlightEntityDto> flightEntityDtoList;
    private BookingEntityDto bookingEntityDto;
    private List<BookingEntityDto> bookingEntityDtoList;
    private FlightScheduleDto  flightScheduleDto;
    private List<FlightScheduleDto> flightScheduleDtoList;

    private SeatInventoryDTO seatInventoryDTO;
    private  List<SeatInventoryDTO>  seatInventoryDTOList;


    public List<SeatInventoryDTO> getSeatInventoryDTOList() {
        return seatInventoryDTOList;
    }

    public void setSeatInventoryDTOList(List<SeatInventoryDTO> seatInventoryDTOList) {
        this.seatInventoryDTOList = seatInventoryDTOList;
    }

    public SeatInventoryDTO getSeatInventoryDTO() {
        return seatInventoryDTO;
    }

    public void setSeatInventoryDTO(SeatInventoryDTO seatInventoryDTO) {
        this.seatInventoryDTO = seatInventoryDTO;
    }

    public List<FlightScheduleDto> getFlightScheduleDtoList() {
        return flightScheduleDtoList;
    }

    public void setFlightScheduleDtoList(List<FlightScheduleDto> flightScheduleDtoList) {
        this.flightScheduleDtoList = flightScheduleDtoList;
    }

    public FlightScheduleDto getFlightScheduleDto() {
        return flightScheduleDto;
    }

    public void setFlightScheduleDto(FlightScheduleDto flightScheduleDto) {
        this.flightScheduleDto = flightScheduleDto;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntityDto getUser() {
        return user;
    }

    public void setUser(UserEntityDto user) {
        this.user = user;
    }

    public List<UserEntityDto> getUserEntityDtoList() {
        return userEntityDtoList;
    }

    public void setUserEntityDtoList(List<UserEntityDto> userEntityDtoList) {
        this.userEntityDtoList = userEntityDtoList;
    }

    public TIcketEntityDto gettIcketEntityDto() {
        return tIcketEntityDto;
    }

    public void settIcketEntityDto(TIcketEntityDto tIcketEntityDto) {
        this.tIcketEntityDto = tIcketEntityDto;
    }

    public List<TIcketEntityDto> getTicketEntityList() {
        return ticketEntityList;
    }

    public void setTicketEntityList(List<TIcketEntityDto> ticketEntityList) {
        this.ticketEntityList = ticketEntityList;
    }

    public FlightEntityDto getFlightEntityDto() {
        return flightEntityDto;
    }

    public void setFlightEntityDto(FlightEntityDto flightEntityDto) {
        this.flightEntityDto = flightEntityDto;
    }

    public List<FlightEntityDto> getFlightEntityDtoList() {
        return flightEntityDtoList;
    }

    public void setFlightEntityDtoList(List<FlightEntityDto> flightEntityDtoList) {
        this.flightEntityDtoList = flightEntityDtoList;
    }

    public BookingEntityDto getBookingEntityDto() {
        return bookingEntityDto;
    }

    public void setBookingEntityDto(BookingEntityDto bookingEntityDto) {
        this.bookingEntityDto = bookingEntityDto;
    }

    public List<BookingEntityDto> getBookingEntityDtoList() {
        return bookingEntityDtoList;
    }

    public void setBookingEntityDtoList(List<BookingEntityDto> bookingEntityDtoList) {
        this.bookingEntityDtoList = bookingEntityDtoList;
    }
}
