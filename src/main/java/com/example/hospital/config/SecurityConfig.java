package com.example.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .anyRequest().authenticated())
                                .httpBasic();

                return http.build();
        }

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {

                UserDetails doctor = User.withUsername("doctor")
                                .password("{noop}123")
                                .roles("DOCTOR")
                                .build();

                UserDetails nurse = User.withUsername("nurse")
                                .password("{noop}123")
                                .roles("NURSE")
                                .build();

                UserDetails patient = User.withUsername("patient")
                                .password("{noop}123")
                                .roles("PATIENT")
                                .build();

                return new InMemoryUserDetailsManager(doctor, nurse, patient);
        }
}