package com.example.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // DIAGNOSES — тільки лікар
                        .requestMatchers("/diagnoses/**").hasRole("DOCTOR")

                        // TREATMENTS — лікар і медсестра
                        .requestMatchers("/treatments/**").hasAnyRole("DOCTOR", "NURSE")

                        // EXECUTIONS — лікар і медсестра
                        .requestMatchers("/executions/**").hasAnyRole("DOCTOR", "NURSE")

                        // PATIENTS — всі ролі можуть читати (для простоти)
                        .requestMatchers("/patients/**").hasAnyRole("DOCTOR", "NURSE", "PATIENT")

                        // все інше — тільки авторизовані
                        .anyRequest().authenticated())
                .httpBasic(); // щоб можна було логінитись через Postman / браузер

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