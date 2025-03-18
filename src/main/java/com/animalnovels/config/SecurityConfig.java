package com.animalnovels.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF temporarily for debugging
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Make sure login page and its processing URL are accessible
                .requestMatchers("/login", "/login?error", "/login?logout", "/perform_login").permitAll()
                // Public resources
                .requestMatchers("/", "/home", "/register", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // Additional public pages
                .requestMatchers("/about", "/contact").permitAll()
                // Secured paths
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/messages/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform_login") // Explicit processing URL
                .defaultSuccessUrl("/home", true) // Force redirect to home
                .failureUrl("/login?error=true") // Explicit failure URL
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );
            
        return http.build();
    }
}