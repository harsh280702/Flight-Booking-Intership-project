package com.fbcode.Fb.Booking.Application.entity.user;

import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationAotProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;  // Store hashed password (BCrypt)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;  // USER, ADMIN

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;


    @Column(nullable = false)
    private String fullName;

    private String phone;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Optional: One user can have multiple bookings
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setUsername(String email) {
        this.username = email.substring(0, email.indexOf("@"));
    }

}
