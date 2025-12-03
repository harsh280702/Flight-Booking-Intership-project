package com.fbcode.Fb.Booking.Application.repositry.booking;

import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepositry extends JpaRepository<BookingEntity,Integer> {

    boolean existsBypnr(String pnr);
}
