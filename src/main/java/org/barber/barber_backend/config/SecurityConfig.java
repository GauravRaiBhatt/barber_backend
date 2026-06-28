package org.barber.barber_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless REST APIs
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/users/register").permitAll() // Allow public access to registration
//                        .anyRequest().authenticated() // All other requests must be authenticated
//                );
//        return http.build();
//    }
    // In SecurityConfig.java

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Add your GET endpoints here for testing
                        .requestMatchers("/api/v1/users/register", "/api/v1/*/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
