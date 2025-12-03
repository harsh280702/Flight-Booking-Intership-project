package com.fbcode.Fb.Booking.Application.service.InterFace.ticket;

import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITicketServices {

    Mono<UserResponseDTO> createTicket(TicketEntity ticketEntity);
    Mono<UserResponseDTO> updateTicket(TicketEntity ticketEntity);
    Mono<UserResponseDTO> deleteTicket(TicketEntity ticketEntity);
    Flux<TicketEntity> getTickets();
    Mono<TicketEntity> getTicket(String ticketId);



}
