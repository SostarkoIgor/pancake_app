package com.igor.pancake.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails customer = User.withUsername("customer")
                .password(passwordEncoder().encode("croz"))
                .roles("CUSTOMER")
                .build();

        UserDetails employee = User.withUsername("employee")
                .password(passwordEncoder().encode("palacinke"))
                .roles("EMPLOYEE")
                .build();

        UserDetails owner = User.withUsername("owner")
                .password(passwordEncoder().encode("store"))
                .roles("OWNER")
                .build();

        return new InMemoryUserDetailsManager(customer, employee, owner);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/reports/**").hasRole("OWNER")
                        .requestMatchers("/ingredients/**").hasAnyRole("EMPLOYEE")
                        .requestMatchers("/orders/**").hasAnyRole("CUSTOMER")
                        .requestMatchers("/pancakes/**").hasAnyRole("CUSTOMER")
                        .anyRequest().authenticated()
                )

                .httpBasic(withDefaults());

        return http.build();
    }
}

