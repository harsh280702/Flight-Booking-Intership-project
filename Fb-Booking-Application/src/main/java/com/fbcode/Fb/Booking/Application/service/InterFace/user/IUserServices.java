package com.fbcode.Fb.Booking.Application.service.InterFace.user;

import com.fbcode.Fb.Booking.Application.dto.AuthRespone;
import com.fbcode.Fb.Booking.Application.dto.LoginResponse;
import com.fbcode.Fb.Booking.Application.dto.user.UserEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.user.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserServices {



    /**
     * Add a new user (Reactive).
     *
     * @param userEntity request body with user details
     * @return Mono containing UserResponseDTO
     */
    Mono<UserResponseDTO> addUser(UserEntity userEntity);

    /**
     * Update an existing user by ID.
     *
     * @param id          user's ID
     * @param userEntity  new user data
     * @return Mono containing updated UserResponseDTO
     */
    Mono<UserResponseDTO> updateUser(Long id, UserEntity userEntity);

    /**
     * Delete a user by ID.
     *
     * @param id user ID
     * @return Mono with delete response DTO
     */
    Mono<String> deleteUser(Long id);

    /**
     * Fetch all users in a reactive stream.
     *
     * @return Flux of UserResponseDTO
     */
    Flux<UserResponseDTO> getAllUsers();

    /**
     * Get all bookings made by a specific user.
     *
     * @param username username
     * @return Flux of UserResponseDTO containing bookings
     */
    Flux<UserResponseDTO> getUsersBookings(String username);

    /**
     * Get user details by ID.
     *
     * @param id user ID
     * @return Mono of UserResponseDTO
     */
    Mono<UserResponseDTO> getUserById(Long id);

    /**
     * Get a user by username.
     *
     * @param username username
     * @return Mono of UserResponseDTO
     */
    Mono<UserResponseDTO> getUserByUsername(String username);

    /**
     * Get user by email.
     *
     * @param email email address
     * @return Mono of UserResponseDTO
     */
    Mono<UserResponseDTO> getUserByEmail(String email);

    /**
     * Get user by phone number.
     *
     * @param phone mobile number
     * @return Mono of UserResponseDTO
     */
    Mono<UserResponseDTO> getUserByPhone(String phone);

    /**
     * Reactive login method.
     *
     * @param loginResponse login credentials
     * @return Mono of AuthResponse containing JWT token
     */
    Mono<AuthRespone> login(LoginResponse loginResponse);

    /**
     * Reactive logout method to invalidate JWT token.
     *
     * @param token JWT token to invalidate
     * @return Mono containing success message string
     */
    Mono<String> Logout(String token);




}
