package com.fbcode.Fb.Booking.Application.controller.user;

import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.dto.user.UserEntityDto;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import com.fbcode.Fb.Booking.Application.service.InterFace.user.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private IUserServices  userServices;
    private JavaMailSender mailSender;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthRespone>> login(@RequestBody LoginResponse loginResponse) {

        if(loginResponse==null){
            Mono.just( ResponseEntity.status(HttpStatus.NO_CONTENT).body("Please Enter the Login Passwrd"));
        }

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

    @PostMapping("/register")
    public  Mono<ResponseEntity<UserEntityDto>> register(@RequestBody UserEntity userEntity) {


        return userServices.addUser(userEntity)
                .flatMap(userResponseDTO -> {

                    // Send email async (non-blocking to client)
                    Mono.fromRunnable(() -> {
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(userEntity.getEmail());
                        mail.setSubject("Registration Successful");
                        mail.setText(
                                "Thank you for registering!\n\n" +
                                        "Username: " + userEntity.getUsername() + "\n" +
                                        "Password: " + userEntity.getPassword()
                        );
                        mailSender.send(mail);
                    }).subscribeOn(Schedulers.boundedElastic()).subscribe();

                    return Mono.just(
                            ResponseEntity
                                    .status(userResponseDTO.getStatusCode())
                                    .body(userResponseDTO.getUser())
                    );
                })
                .onErrorResume(error ->
                        Mono.just(
                                ResponseEntity
                                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(null)
                        )
                );
    }
}
