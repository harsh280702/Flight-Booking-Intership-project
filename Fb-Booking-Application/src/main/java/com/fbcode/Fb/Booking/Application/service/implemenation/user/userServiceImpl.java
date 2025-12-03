package com.fbcode.Fb.Booking.Application.service.implemenation.user;

import com.fbcode.Fb.Booking.Application.config.JwtUtil;
import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.dto.user.UserEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.user.TokenBlacklist;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import com.fbcode.Fb.Booking.Application.exception.OurException;
import com.fbcode.Fb.Booking.Application.mapper.Utils;
import com.fbcode.Fb.Booking.Application.repositry.booking.BookingRepositry;
import com.fbcode.Fb.Booking.Application.repositry.user.TokenBlacklistRepository;
import com.fbcode.Fb.Booking.Application.repositry.user.UserRepositry;
import com.fbcode.Fb.Booking.Application.service.InterFace.user.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class userServiceImpl implements IUserServices
{

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    @Autowired
    private BookingRepositry bookingRepositry;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private JwtUtil jwtUtil;

    // ---------------------------------------------------------
    // ADD USER
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> addUser(UserEntity userEntity) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                // Check duplicate email
                if (userRepositry.finUserEntityByEmail(userEntity.getEmail()).isPresent()) {
                    response.setStatusCode(300);
                    response.setMessage("Email Already Exists");
                    return response;
                }

                // Encode password
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

                // Save User
                UserEntity savedUser = userRepositry.save(userEntity);

                response.setUser(Utils.mappedUserEntityToUserEntityDto(savedUser));
                response.setStatusCode(200);
                response.setMessage("User created successfully");

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // UPDATE USER
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> updateUser(Long id, UserEntity userEntity) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity existing = userRepositry.findById(id)
                        .orElseThrow(() -> new OurException("User Not Found"));

                existing.setUsername(userEntity.getUsername());
                existing.setFullName(userEntity.getFullName());
                existing.setEmail(userEntity.getEmail());
                existing.setGender(userEntity.getGender());
                existing.setRole(userEntity.getRole());
                existing.setPhone(userEntity.getPhone());
                existing.setUpdatedAt(LocalDateTime.now());

                // Encode new password
                existing.setPassword(passwordEncoder.encode(userEntity.getPassword()));

                userRepositry.save(existing);

                response.setUser(Utils.mappedUserEntityToUserEntityDto(existing));
                response.setStatusCode(200);
                response.setMessage("User updated successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // DELETE USER
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> deleteUser(Long id) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity entity = userRepositry.findById(id)
                        .orElseThrow(() -> new OurException("User Not Found"));

                userRepositry.delete(entity);

                response.setStatusCode(200);
                response.setMessage("User deleted successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // GET ALL USERS (Flux)
    // ---------------------------------------------------------
    @Override
    public Flux<UserResponseDTO> getAllUsers() {

        return Mono.fromCallable(() -> {

                    UserResponseDTO response = new UserResponseDTO();

                    try {
                        List<UserEntity> users = userRepositry.findAll();

                        List<UserEntityDto> dtoList =
                                Utils.mappedUserEntityToUserEntityDtoList(users);

                        response.setUserEntityDtoList(dtoList);
                        response.setStatusCode(200);
                        response.setMessage("Users fetched successfully");

                    } catch (Exception e) {
                        response.setStatusCode(500);
                        response.setMessage(e.getMessage());
                    }

                    return response;

                }).flatMapMany(Flux::just)
                .delayElements(Duration.ofSeconds(1))
                .subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // GET USERS BOOKINGS (Flux)
    // ---------------------------------------------------------
    @Override
    public Flux<UserResponseDTO> getUsersBookings(String username) {
//        return Flux.fromCallable(() -> {
//
//            UserResponseDTO response = new UserResponseDTO();
//
//            try {
//                List<BookingEntity> bookings =
//                        bookingRepositry.findBookingsByUsername(username);
//
//                response.setBookingList(Utils.mapBookings(bookings));
//                response.setStatusCode(200);
//                response.setMessage("Bookings fetched successfully");
//
//            } catch (Exception e) {
//                response.setStatusCode(500);
//                response.setMessage(e.getMessage());
//            }
//
//            return response;
//
//        }).subscribeOn(Schedulers.boundedElastic());
        return null;
    }


    // ---------------------------------------------------------
    // GET USER BY ID
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> getUserById(Long id) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity entity = userRepositry.findById(id)
                        .orElseThrow(() -> new OurException("User Not Found"));

                response.setUser(Utils.mappedUserEntityToUserEntityDto(entity));
                response.setStatusCode(200);
                response.setMessage("User fetched successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // GET USER BY USERNAME
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> getUserByUsername(String username) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity entity = userRepositry.findByUsername(username)
                        .orElseThrow(() -> new OurException("User Not Found"));

                response.setUser(Utils.mappedUserEntityToUserEntityDto(entity));
                response.setStatusCode(200);
                response.setMessage("User fetched successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // GET USER BY EMAIL
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> getUserByEmail(String email) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity entity = userRepositry.finUserEntityByEmail(email)
                        .orElseThrow(() -> new OurException("User Not Found"));

                response.setUser(Utils.mappedUserEntityToUserEntityDto(entity));
                response.setStatusCode(200);
                response.setMessage("User fetched successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // GET USER BY PHONE
    // ---------------------------------------------------------
    @Override
    public Mono<UserResponseDTO> getUserByPhone(String phone) {

        return Mono.fromCallable(() -> {

            UserResponseDTO response = new UserResponseDTO();

            try {
                UserEntity entity = userRepositry.findUserEntityByPhone(phone)
                        .orElseThrow(() -> new OurException("User Not Found"));

                response.setUser(Utils.mappedUserEntityToUserEntityDto(entity));
                response.setStatusCode(200);
                response.setMessage("User fetched successfully");

            } catch (OurException e) {
                response.setStatusCode(400);
                response.setMessage(e.getMessage());
            }

            return response;

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------
    @Override
    public Mono<AuthRespone> login(LoginResponse loginResponse) {

        return Mono.fromCallable(() -> {

            UserEntity entity = userRepositry.finUserEntityByEmail(loginResponse.getEmail())
                    .orElseThrow(() -> new OurException("User Not Found"));

            if (!passwordEncoder.matches(loginResponse.getPassword(), entity.getPassword())) {
                throw new OurException("Invalid Password");
            }

            String token = jwtUtil.generateToken(entity.getUsername(), String.valueOf(entity.getRole()));

            return new AuthRespone(token, "Login successful");

        }).subscribeOn(Schedulers.boundedElastic());
    }


    // ---------------------------------------------------------
    // LOGOUT
    // ---------------------------------------------------------
    @Override
    public Mono<String> Logout(String token) {

        return Mono.fromCallable(() -> {

                    if (tokenBlacklistRepository.existsByToken(token)) {
                        return "Token already logged out";
                    }

                    long expiry = jwtUtil.getExpirationTime(token);

                    TokenBlacklist blacklist = new TokenBlacklist();
                    blacklist.setToken(token);
                    blacklist.setExpirationTime(expiry);
                    tokenBlacklistRepository.save(blacklist);

                    return "Logout successful";

                }).delayElement(Duration.ofSeconds(1))
                .subscribeOn(Schedulers.boundedElastic());
    }

}



