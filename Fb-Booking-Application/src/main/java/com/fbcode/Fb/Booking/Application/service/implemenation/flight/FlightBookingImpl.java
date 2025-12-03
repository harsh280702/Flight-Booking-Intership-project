package com.fbcode.Fb.Booking.Application.service.implemenation.flight;


import com.fbcode.Fb.Booking.Application.dto.flight.AirPortDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightEntityDto;
import com.fbcode.Fb.Booking.Application.dto.flight.FlightScheduleDto;
import com.fbcode.Fb.Booking.Application.dto.flight.SeatInventoryDTO;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.flight.AirportEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import com.fbcode.Fb.Booking.Application.entity.flight.SeatInventory;
import com.fbcode.Fb.Booking.Application.exception.OurException;
import com.fbcode.Fb.Booking.Application.mapper.Utils;
import com.fbcode.Fb.Booking.Application.repositry.flight.AirportRepositry;
import com.fbcode.Fb.Booking.Application.repositry.flight.FlightRepository;
import com.fbcode.Fb.Booking.Application.repositry.flight.FlightScheduleRepository;
import com.fbcode.Fb.Booking.Application.repositry.flight.SeatInventoryRepository;
import com.fbcode.Fb.Booking.Application.service.InterFace.flight.IFlightServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightBookingImpl implements IFlightServices {

    private FlightRepository   flightRepository;
    private FlightScheduleRepository flightScheduleRepository;
    private SeatInventoryRepository seatInventoryRepository;
    private AirportRepositry  airportRepositry;





    @Override
    public Mono<UserResponseDTO> addFlight(FlightEntity flightEntity) {
       UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightEntity flightEntity1  = flightRepository.save(flightEntity);
                FlightEntityDto flightEntityDto  = Utils.mappedFlightEntityToFlightEntityDto(flightEntity1);
                userResponseDTO.setFlightEntityDto(flightEntityDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic())
                .delayElement(Duration.ofSeconds(2));
    }


    @Override
    public Mono<UserResponseDTO> updateFlight(String flightNumber, FlightEntity flightEntity) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightEntity flightEntity1  = flightRepository.findByFlightNumber(flightNumber).orElseThrow(()->new OurException("flight number not found"));
               flightEntity1.setAircraftType(flightEntity.getAircraftType());
               flightEntity1.setCarrier(flightEntity.getCarrier());
               flightEntity1.setTotalSeats(flightEntity.getTotalSeats());

              FlightEntity flightEntity2= flightRepository.save(flightEntity1);

                FlightEntityDto flightEntityDto  = Utils.mappedFlightEntityToFlightEntityDto(flightEntity);

                userResponseDTO.setFlightEntityDto(flightEntityDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> deleteFlight(String flightNumber) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightEntity flightEntity = flightRepository.findByFlightNumber(flightNumber).orElseThrow(()->new OurException("flight number not found"));

                    flightRepository.delete(flightEntity);
                    userResponseDTO.setStatusCode(200);
                    userResponseDTO.setMessage("added successfully");


            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> findFlightByFlightNumber(String flightNumber) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightEntity flightEntity = flightRepository.findByFlightNumber(flightNumber).orElseThrow(()->new OurException("flight number not found"));
                FlightEntityDto flightEntityDto  =  Utils.mappedFlightEntityToFlightEntityDto(flightEntity);
                userResponseDTO.setFlightEntityDto(flightEntityDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<UserResponseDTO> findLargeCapacityFlights(int minSeats) {
        return null;
    }

    @Override
    public Flux<UserResponseDTO> searchFlightsByCarrier(String carrier) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
               List<FlightEntity> flightEntity = flightRepository.findByCarrier(carrier);
               if (flightEntity.isEmpty())
               {
                   userResponseDTO.setStatusCode(400);
                   userResponseDTO.setMessage("carrier not found");
               }
                List<FlightEntityDto> flightEntityDto  =  Utils.mappedFlightEntityListToFlightEntityDtoList(flightEntity);
                userResponseDTO.setFlightEntityDtoList(flightEntityDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).flatMapMany(Flux::just)
                .delayElements(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> addFlightSchedule(FlightSchedule flightSchedule) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightSchedule flightSchedule1 = flightScheduleRepository.save(flightSchedule);
                FlightScheduleDto flightScheduleDto = Utils.mappedFlightScheduleEntityToFlightScheduleDto(flightSchedule1);
                userResponseDTO.setFlightScheduleDto(flightScheduleDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> updateFlightSchedule(Long id, FlightSchedule flightSchedule) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                FlightSchedule flightSchedule1 = flightScheduleRepository.findById(id).orElseThrow(()->new OurException("flight schedule not found"));
                if(flightSchedule1!=null){
                    flightSchedule1.setBasePrice(flightSchedule.getBasePrice());
                    flightSchedule1.setOrigin(flightSchedule.getOrigin());
                    flightSchedule1.setFlight(flightSchedule.getFlight());
                    flightSchedule1.setSeats(flightSchedule.getSeats());
                    flightSchedule1.setArrivalTime(flightSchedule.getArrivalTime());
                    flightSchedule1.setDestination(flightSchedule.getDestination());
                    flightScheduleRepository.save(flightSchedule1);
                }
                FlightScheduleDto flightScheduleDto = Utils.mappedFlightScheduleEntityToFlightScheduleDto(flightSchedule1);
                userResponseDTO.setFlightScheduleDto(flightScheduleDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).delayElement(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> findFlightScheduleByFlightNumber(String flightNumber) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{
            try
            {

                FlightSchedule flightSchedule = flightScheduleRepository.findByFlightNumber(flightNumber).orElseThrow(() -> new OurException("flight schedule not found"));
                FlightScheduleDto flightScheduleDto = Utils.mappedFlightScheduleEntityToFlightScheduleDto(flightSchedule);
                userResponseDTO.setFlightScheduleDto(flightScheduleDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<UserResponseDTO> searchFlightScheduleByCarrier(Long originId, Long destId, LocalDate date) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(() -> {

                    try {
                        List<FlightSchedule> flightSchedules = flightScheduleRepository.findByRouteAndDate(originId, destId, date).orElseThrow(() -> new OurException("their is no flight"));
                        List<FlightScheduleDto> flightScheduleDto = Utils.mappedFlightScheduleEntityListToFlightScheduleDtoList(flightSchedules);
                        userResponseDTO.setFlightScheduleDtoList(flightScheduleDto);
                        userResponseDTO.setStatusCode(200);
                        userResponseDTO.setMessage("added successfully");
                    } catch (OurException e) {
                        userResponseDTO.setStatusCode(400);
                        userResponseDTO.setMessage(e.getMessage());
                    } catch (Exception e) {
                        userResponseDTO.setStatusCode(500);
                        userResponseDTO.setMessage(e.getMessage());
                    }
                    return userResponseDTO;
                }).flatMapMany(Flux::just)
                .delayElements(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> findStatusOFFlightbyFlightNumber(String flightNumber) {

       return Mono.fromCallable(() -> {
                       String status = flightScheduleRepository.findStatusByFlightNumber(flightNumber);
                       return status;
                })
                .delayElement(Duration.ofSeconds(2))
                .subscribeOn(Schedulers.boundedElastic());
    }


    @Override
    public Flux<UserResponseDTO> getAvailableSeats(Long id) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
               List<SeatInventory> seatInventory = seatInventoryRepository.findAvailableSeatsByScheduleId(id).orElseThrow(()->new OurException("seat inventory not found"));
               List<SeatInventoryDTO>  seatInventoryDTOS = Utils.mappedSeatInventoryEntityToSeatInventoryDto(seatInventory);
               userResponseDTO.setSeatInventoryDTOList(seatInventoryDTOS);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).flatMapMany(Flux::just)
                 .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Long> countAvailableSeats(Long id) {
        return  Mono.fromCallable(()->
        {
            Long seatNumber= seatInventoryRepository.countByScheduleIdAndIsReservedFalse(id );
            return seatNumber;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<UserResponseDTO> getSeatInventory(Long id) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
               List<SeatInventory> seatInventory = seatInventoryRepository.findAvailableSeatsByScheduleId(id).orElseThrow(()-> new OurException("no Inventory"));
               List<SeatInventoryDTO> seatInventoryDTOS = Utils.mappedSeatInventoryEntityToSeatInventoryDto(seatInventory);
                userResponseDTO.setSeatInventoryDTOList(seatInventoryDTOS);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        }).flatMapMany(Flux::just)
                .subscribeOn(Schedulers.boundedElastic());
    }



    @Override
    public Flux<UserResponseDTO> findAirportByCity(String city) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        return Mono.fromCallable(()->{

            try
            {
                List<AirportEntity> airportEntity = airportRepositry.findAirportByCity(city).orElseThrow(() -> new OurException("No airport  found in this city"));
                List<AirPortDto> airPortDtos= Utils.mappedAirportEntityListToAirportDtoList(airportEntity);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("added successfully");
            }
            catch(OurException e){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(e.getMessage());
            }
            catch(Exception e)
            {
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage(e.getMessage());
            }
            return userResponseDTO;
        })
                .flatMapMany(Flux::just)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
