package com.fbcode.Fb.Booking.Application.repositry;

import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

}
