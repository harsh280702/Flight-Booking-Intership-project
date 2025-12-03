package com.fbcode.Fb.Booking.Application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationManager  jwtAuthenticationManager;

    private  final JwtSecurityContextRepository jwtSecurityContextRepository;

    public SecurityConfig(JwtAuthenticationManager jwtAuthenticationManager, JwtSecurityContextRepository jwtSecurityContextRepository) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.jwtSecurityContextRepository = jwtSecurityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {

        http.csrf(httpSecurityCsrfConfigurer ->  httpSecurityCsrfConfigurer.disable())
                .cors(corsConfigurer ->corsConfigurer.disable())
                .authenticationManager(jwtAuthenticationManager)
                .securityContextRepository(jwtSecurityContextRepository)
                .authorizeExchange(exchange ->exchange
                        .pathMatchers("/auth/register", "/auth/login").permitAll()
                        .pathMatchers("/flights/search").permitAll()
                        .pathMatchers("/").hasRole("ADMIN")
                        .pathMatchers("/").hasRole("USER")
                       .anyExchange().authenticated())
                .httpBasic(httpBasicConfigurer ->httpBasicConfigurer.disable())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
