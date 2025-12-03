package com.fbcode.Fb.Booking.Application.controller.user;


import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.service.InterFace.user.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RequestMapping
@RestController
public class UserController {

    @Autowired
    private IUserServices userServices;


    @PostMapping("/login")
    public CompletableFuture<Mono<ResponseEntity<AuthRespone>>> login( @RequestBody LoginResponse loginResponse ) {

        if(loginResponse != null) {
             Mono<AuthRespone> authResponeMono = userServices

        }

    }

}
