package com.fbcode.Fb.Booking.Application.controller.user;


import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.dto.user.UserEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import com.fbcode.Fb.Booking.Application.service.InterFace.user.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {


    private IUserServices userServices;

    private JavaMailSender mailSender;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthRespone>> login( @RequestBody LoginResponse loginResponse) {

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


    @PutMapping("/update/{id}")
    public  Mono<ResponseEntity<UserEntityDto>> updateUser(@PathVariable("id") Long id ,   @RequestBody UserEntity  userEntity) {
        UserResponseDTO   userResponseDTO =  new UserResponseDTO();
        return userServices.updateUser(id,userEntity)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getUser()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));


    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping()
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable("id") Long id ){
        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.deleteUser(id)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getMessage()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDTO.getMessage())));
    }


    @PreAuthorize(value = "hasAnyRole('Admin','USER')")
    @PostMapping("/logout/{logout}")
    public Mono<ResponseEntity<String>> Logout(@PathVariable("logout") String token ){

        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.Logout(token)
                .map(userResponseDTO1 ->   ResponseEntity.status( 200).body("Logout Successfully"))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));


    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/get-email")
    public Mono<ResponseEntity<UserEntityDto>> userByEmail (@PathVariable("email") String email){
        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.getUserByEmail(email)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getUser()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));
    }


    @PreAuthorize("hasrole('USER','ADMIN')")
    @GetMapping("/user/{username}")
    public Mono<ResponseEntity<UserEntityDto>> getUserByUserName(@PathVariable("username") String username){
        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.getUserByUsername(username)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getUser()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));
    }


           @PreAuthorize("hasRole('ADMIN')")
           @GetMapping("/all-users")
          public Flux<ResponseEntity<UserEntityDto>> Users(){
               UserResponseDTO  userResponseDTO =  new UserResponseDTO();
               return userServices.getAllUsers()
                       .map(user -> ResponseEntity
                               .status(HttpStatus.OK)
                               .body(userResponseDTO.getUser()))
                       .onErrorResume(error ->
                               Flux.just(ResponseEntity
                                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                       .body(null)));}

    @PreAuthorize("hasrole('USER','ADMIN')")
    @GetMapping("/get-userBy-Phone/{phone}")
    public Mono<ResponseEntity<UserEntityDto>> getUserByPhone(@PathVariable("phone") String phone){
        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.getUserByPhone(phone)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getUser()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));
    }


    @PreAuthorize("hasrole('USER','ADMIN')")
    @GetMapping("/get-user-by-Id/{id}")
    public Mono<ResponseEntity<UserEntityDto>> getUserById(@PathVariable("id") long id ){
        UserResponseDTO userResponseDTO =  new UserResponseDTO();
        return userServices.getUserById(id)
                .map(userResponseDTO1 ->   ResponseEntity.status( userResponseDTO.getStatusCode()).body(userResponseDTO.getUser()))


                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)));
    }



}
