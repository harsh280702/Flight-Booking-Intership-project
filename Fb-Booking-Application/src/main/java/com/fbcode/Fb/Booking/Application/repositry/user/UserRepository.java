package com.fbcode.Fb.Booking.Application.repositry.user;

import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
    boolean findByEmail(String email);

    @Override
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findUserEntityByEmail(String email);



    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findUserEntityByPhone(String phone);
}
