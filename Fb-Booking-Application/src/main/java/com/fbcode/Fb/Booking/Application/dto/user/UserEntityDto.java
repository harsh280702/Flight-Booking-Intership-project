package com.fbcode.Fb.Booking.Application.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import com.fbcode.Fb.Booking.Application.entity.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityDto {
    private Long id;
    private String username;
    private String email;
    private String password;  // Store hashed password (BCrypt)
    private Role role = Role.USER;  // USER, ADMIN
    private String fullName;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<BookingEntity> bookings = new ArrayList<>();

}
