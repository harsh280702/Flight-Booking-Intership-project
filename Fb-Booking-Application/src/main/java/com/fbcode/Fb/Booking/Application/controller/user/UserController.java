package com.fbcode.Fb.Booking.Application.controller.user;


import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.service.InterFace.user.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {


    private IUserServices userServices;

    private JavaMailSender mailSender;

    @GetMapping("/login")
    public Mono<ResponseEntity<AuthRespone>> login( @RequestBody LoginResponse loginResponse) {
        return userServices.login(loginResponse)
                .map(authRespone ->     ResponseEntity.ok()
                        .header("X-Auth-stauts", "SUCCESS")
                        .header("X-Auth-token", authRespone.getToken())
                        .header("X-Request-Method", "POST")
                        .header("X-Request-id",java.util.UUID.randomUUID().toString())
                        .header("X-App-Version", "1.0.0")
                        .body(authRespone))
                .onErrorResume(throwable -> Mono.just(
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .header("X-Auth-stauts", "FAILURE")
                                .header("X-Error-Message", throwable.getMessage())
                                .build())
                );
        }



}
