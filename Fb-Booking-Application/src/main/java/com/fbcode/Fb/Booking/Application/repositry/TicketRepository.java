package com.fbcode.Fb.Booking.Application.repositry;

import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

    Optional<TicketEntity> findticketById(Long id);
}
