package com.fbcode.Fb.Booking.Application.repositry.user;

import com.fbcode.Fb.Booking.Application.entity.user.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Integer> {
    boolean existsByToken(String token);

}
