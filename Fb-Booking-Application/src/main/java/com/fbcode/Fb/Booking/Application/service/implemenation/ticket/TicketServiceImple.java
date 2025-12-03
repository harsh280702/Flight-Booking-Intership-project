package com.fbcode.Fb.Booking.Application.service.implemenation.ticket;

import com.fbcode.Fb.Booking.Application.dto.ticket.TIcketEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import com.fbcode.Fb.Booking.Application.exception.OurException;
import com.fbcode.Fb.Booking.Application.mapper.Utils;
import com.fbcode.Fb.Booking.Application.repositry.TicketRepository;
import com.fbcode.Fb.Booking.Application.service.InterFace.ticket.ITicketServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class TicketServiceImple  implements ITicketServices {

    private final TicketRepository  ticketRepository;

    @Override
    public Mono<UserResponseDTO> createTicket(TicketEntity ticketEntity) {
        return Mono.fromCallable(()->{
                   UserResponseDTO userResponseDTO = new UserResponseDTO();
                   try{
                       TicketEntity ticketEntity1 =ticketRepository.save(ticketEntity);
                       TIcketEntityDto tIcketEntityDto = Utils.mappedTicketEntityToTicketEntityDto(ticketEntity1);
                       userResponseDTO.settIcketEntityDto(tIcketEntityDto);
                       userResponseDTO.setStatusCode(200);
                       userResponseDTO.setMessage("Success");
                   }
                   catch (OurException ex){
                       userResponseDTO.setStatusCode(400);
                       userResponseDTO.setMessage(ex.getMessage());
                   }
                   catch(Exception e){
                       userResponseDTO.setStatusCode(500);
                       userResponseDTO.setMessage("FAILURE"+e.getMessage());
                   }

                    return userResponseDTO;

        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> updateTicket(TicketEntity ticketEntity) {
        return null;
    }

    @Override
    public Mono<UserResponseDTO> deleteTicket(TicketEntity ticketEntity) {
        return null;
    }

    @Override
    public Flux<TicketEntity> getTickets() {
        return null;
    }

    @Override
    public Mono<TicketEntity> getTicket(String ticketId) {
        return null;
    }
}
