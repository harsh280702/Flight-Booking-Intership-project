package com.fbcode.Fb.Booking.Application.service.implemenation.booking;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.flight.FlightSchedule;
import com.fbcode.Fb.Booking.Application.entity.flight.SeatInventory;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import com.fbcode.Fb.Booking.Application.exception.OurException;
import com.fbcode.Fb.Booking.Application.repositry.booking.BookingRepositry;
import com.fbcode.Fb.Booking.Application.repositry.flight.FlightScheduleRepository;
import com.fbcode.Fb.Booking.Application.repositry.flight.SeatInventoryRepository;
import com.fbcode.Fb.Booking.Application.repositry.user.UserRepository;
import com.fbcode.Fb.Booking.Application.service.InterFace.booking.IBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBooking {

    private final BookingRepositry bookingRepository;
    private final SeatInventoryRepository seatInventoryRepository;
    private final FlightScheduleRepository flightScheduleRepository;
    private final UserRepository userRepository;
    private final  PNRGenerator    pnrGenerator;


    @Override
    public Mono<UserResponseDTO> bookTIcker(BookingEntity bookingEntity) {

        return Mono.fromCallable(()->{
            UserResponseDTO userResponseDTO = new UserResponseDTO();

            try{

                // 1. Validate User
                UserEntity user = userRepository.findById(bookingEntity.getUser().getId())
                        .orElseThrow(() -> new OurException("User not found"));

                // 2. Validate Schedule
                FlightSchedule schedule = flightScheduleRepository.findById(bookingEntity.getFlight().getId())
                        .orElseThrow(() -> new OurException("Flight schedule not found"));

                // 3. Check available seat
                SeatInventory seat = seatInventoryRepository
                        .findAvailableSeatsByScheduleIdAndClass(schedule.getId(), String.valueOf(schedule.getClass()))
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new OurException("No seats available"));


                bookingEntity.setPnr(pnrGenerator.toString());

            }
            catch (OurException ex){

            }
            catch (Exception e){

            }

            return userResponseDTO;
        }).subscribeOn(Schedulers.boundedElastic());
    }





private String generateRef() {
    return "BR-" + UUID.randomUUID();
}


private BigDecimal calculateTotalAmount(BigDecimal basePrice, int passengerCount) {
    return basePrice.multiply(BigDecimal.valueOf(passengerCount));
}

    @Override
    public Mono<UserResponseDTO> cancelBooking(String flightId) {
        return null;
    }




}
