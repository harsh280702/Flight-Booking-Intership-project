package com.fbcode.Fb.Booking.Application.service.implemenation.ticket;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import com.fbcode.Fb.Booking.Application.service.InterFace.ticket.ITicketServices;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class TicketServiceImple  implements ITicketServices {

    @Override
    public Mono<UserResponseDTO> createTicket(TicketEntity ticketEntity) {
        return Mono.fromCallable(()->{
UserResponseDTO userResponseDTO = new UserResponseDTO();


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
