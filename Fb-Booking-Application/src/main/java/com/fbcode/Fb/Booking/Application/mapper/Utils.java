package com.fbcode.Fb.Booking.Application.mapper;

import com.fbcode.Fb.Booking.Application.dto.booking.BookingEntityDto;
import com.fbcode.Fb.Booking.Application.dto.flight.AirPortDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightEntityDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightScheduleDto;
import com.fbcode.Fb.Booking.Application.dto.flight.SeatInventoryDTO;
import com.fbcode.Fb.Booking.Application.dto.ticket.TIcketEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserEntityDto;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.AirportEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import com.fbcode.Fb.Booking.Application.entity.flight.SeatInventory;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {



    // ---------------------------------------------------------
    // This function is use to the map the UserEntity TO User Entity DTo
    // ---------------------------------------------------------
    public  static UserEntityDto  mappedUserEntityToUserEntityDto(UserEntity userEntity){
        UserEntityDto userEntityDto = new UserEntityDto();
        userEntityDto.setId(userEntity.getId());
        userEntityDto.setUsername(userEntity.getUsername());
        userEntityDto.setFullName(userEntity.getFullName());
        userEntityDto.setEmail(userEntity.getEmail());
        userEntityDto.setPhone(userEntity.getPhone());
        userEntityDto.setPassword(userEntity.getPassword());
        userEntityDto.setRole(userEntity.getRole());
        userEntityDto.setBookings(userEntity.getBookings());
        return userEntityDto;
    }

    // ---------------------------------------------------------
    // THis Function is use  for the map the Booking Entity TO Booking Entity Dto
    // ---------------------------------------------------------
    public  static BookingEntityDto mappedVBookingEntityToBookingEntityDto(BookingEntity bookingEntity){
        BookingEntityDto bookingEntityDto = new BookingEntityDto();
        bookingEntityDto.setBookingId(bookingEntity.getBookingId());
        bookingEntityDto.setPnr(bookingEntity.getPnr());
        bookingEntityDto.setUser(bookingEntity.getUser());
        bookingEntityDto.setFlight(bookingEntity.getFlight());
        bookingEntityDto.setSeats(bookingEntity.getSeats());
        bookingEntityDto.setStatus(bookingEntity.getStatus());
        bookingEntityDto.setTotalAmount(bookingEntity.getTotalAmount());
        bookingEntityDto.setPassengerName(bookingEntity.getPassengerName());
        bookingEntityDto.setPassengerEmail(bookingEntity.getPassengerEmail());
        bookingEntityDto.setPassengerPhone(bookingEntity.getPassengerPhone());
        bookingEntityDto.setBookingDateTime(bookingEntity.getBookingDateTime());
        return bookingEntityDto;
    }
    // ---------------------------------------------------------
    // THis Function is use for  the map the Fligth Entity TO Flight Entity Dto
    // ---------------------------------------------------------

    public  static FlightEntityDto mappedFlightEntityToFlightEntityDto(FlightEntity flightEntity){
        FlightEntityDto flightEntityDto = new FlightEntityDto();
        flightEntityDto.setId(flightEntity.getId());
        flightEntityDto.setFlightNumber(flightEntity.getFlightNumber());
        flightEntityDto.setCarrier(flightEntity.getCarrier());
        flightEntityDto.setAircraftType(flightEntity.getAircraftType());
        flightEntityDto.setTotalSeats(flightEntity.getTotalSeats());
        return flightEntityDto;
    }


    // ---------------------------------------------------------
    // THis  function is use for the map the   Ticket Entity TO Ticket Entity Dto
    // ---------------------------------------------------------
    public static TIcketEntityDto mappedTicketEntityToTicketEntityDto(TicketEntity ticketEntity){
         TIcketEntityDto ticketEntityDto    = new TIcketEntityDto();
         ticketEntityDto.setId(ticketEntity.getId());
         ticketEntityDto.setPnr(ticketEntity.getPnr());
         ticketEntityDto.setUser(ticketEntity.getUser());
         ticketEntityDto.setSeatNumber(ticketEntity.getSeatNumber());
         ticketEntityDto.setStatus(ticketEntity.getStatus());
         ticketEntityDto.setFare(ticketEntity.getFare());
         ticketEntityDto.setBooking(ticketEntity.getBooking());
         ticketEntityDto.setFlight(ticketEntity.getFlight());
         ticketEntityDto.setPassengerName(ticketEntity.getPassengerName());
         ticketEntityDto.setPassengerAge(ticketEntity.getPassengerAge());
         ticketEntityDto.setPassengerGender(ticketEntity.getPassengerGender());
         ticketEntityDto.setCreatedAt(ticketEntity.getCreatedAt());
         ticketEntityDto.setUpdatedAt(ticketEntity.getUpdatedAt());
         return ticketEntityDto;
    }



    // ---------------------------------------------------------
    // THis Function is use for  the map the Fligth Entity TO Flight Entity Dto
    // ---------------------------------------------------------
    public static FlightScheduleDto  mappedFlightScheduleEntityToFlightScheduleDto(FlightSchedule userResponseDTo){
        FlightScheduleDto flightScheduleDto = new FlightScheduleDto();
        flightScheduleDto.setId(userResponseDTo.getId());
        flightScheduleDto.setDestination(userResponseDTo.getDestination());
        flightScheduleDto.setBasePrice(userResponseDTo.getBasePrice());
        flightScheduleDto.setFlight(userResponseDTo.getFlight());
        flightScheduleDto.setOrigin(userResponseDTo.getOrigin());
        flightScheduleDto.setStatus(userResponseDTo.getStatus());
        flightScheduleDto.setDepartureTime(userResponseDTo.getDepartureTime());
        flightScheduleDto.setArrivalTime(userResponseDTo.getArrivalTime());
        flightScheduleDto.setSeats(userResponseDTo.getSeats());
        return flightScheduleDto;
    }

    public  static SeatInventoryDTO mapSeatInventoryEntityToSeatInventoryDto(SeatInventory seatInventoryEntity){
        SeatInventoryDTO seatInventoryDto = new SeatInventoryDTO();
        seatInventoryDto.setId(seatInventoryEntity.getId());
        seatInventoryDto.setSeatNumber(seatInventoryEntity.getSeatNumber());
        seatInventoryDto.setSeatClass(seatInventoryEntity.getSeatClass());
        seatInventoryDto.setIsReserved(seatInventoryEntity.getIsReserved());
        seatInventoryDto.setPriceModifier(seatInventoryEntity.getPriceModifier());
        seatInventoryDto.setScheduleId(seatInventoryEntity.getScheduleId());
        seatInventoryDto.setSchedule(seatInventoryEntity.getSchedule());
        return  seatInventoryDto;
    }

    public static  AirPortDto mappedAirportEntityToAirportDto(AirportEntity airportEntity){
        AirPortDto airportDto = new AirPortDto();
        airportDto.setId(airportEntity.getId());
        airportDto.setName(airportEntity.getName());
        airportDto.setCity(airportEntity.getCity());
        airportDto.setCountry(airportEntity.getCountry());
        airportDto.setDestinationSchedules(airportEntity.getDestinationSchedules());
        airportDto.setOriginSchedules(airportEntity.getOriginSchedules());
        return  airportDto;
    }


    public static List<AirPortDto>  mappedAirportEntityListToAirportDtoList(List<AirportEntity> airportEntityList){
        return airportEntityList.stream().map(Utils::mappedAirportEntityToAirportDto) .collect(Collectors.toList());
    }



    public static List<SeatInventoryDTO> mappedSeatInventoryEntityToSeatInventoryDto(List<SeatInventory> seatInventoryEntity){
        return  seatInventoryEntity.stream().map(Utils::mapSeatInventoryEntityToSeatInventoryDto).collect(Collectors.toList());
    }

    public static List<FlightScheduleDto>   mappedFlightScheduleEntityListToFlightScheduleDtoList(List<FlightSchedule> flightSchedules){
        return flightSchedules.stream().map(Utils::mappedFlightScheduleEntityToFlightScheduleDto).collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // this is function is use TO mapped the Ticket Entity List TO Ticket Entity Dto List
    // ---------------------------------------------------------
    public static List<TIcketEntityDto>  mappedTicketEntityListToTicketEntityDtoList(List<TicketEntity> ticketEntity){
        return  ticketEntity .stream().map(Utils::mappedTicketEntityToTicketEntityDto).collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // this is function is use TO mapped the Flight Entity List TO Flight Entity Dto List
    // ---------------------------------------------------------
    public  static List<FlightEntityDto>  mappedFlightEntityListToFlightEntityDtoList(List<FlightEntity> flightEntities){
        return flightEntities.stream().map(Utils::mappedFlightEntityToFlightEntityDto).collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // this is function is use TO mapped the BooKing Entity List TO Booking Entity Dto List
    // ---------------------------------------------------------
    public static  List<BookingEntityDto>  mappedBookingEntityListToBookingEntityDtoList(List<BookingEntity> bookingEntities){
        return bookingEntities.stream().map(Utils::mappedVBookingEntityToBookingEntityDto).collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // this is function is use TO mapped the User Entity List TO User Entity Dto List
    // ---------------------------------------------------------
    public static List<UserEntityDto> mappedUserEntityToUserEntityDtoList(List<UserEntity> userEntities){
        return userEntities.stream().map(Utils::mappedUserEntityToUserEntityDto).collect(Collectors.toList());
    }

}
