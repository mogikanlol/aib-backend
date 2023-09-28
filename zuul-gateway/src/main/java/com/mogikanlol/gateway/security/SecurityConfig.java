package com.mogikanlol.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter =
                new JwtAuthenticationTokenFilter(jwtTokenProvider, userDetailsService());

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll()
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // test : test
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User
                .withUsername("test")
                .password("$2a$10$vXo124lhpeiifSslvwROqutxcIiyQliIJq4lozW7hlJbk.Zm3xBIa")
                .roles("USER")
                .build();
        detailsManager.createUser(userDetails);
        return detailsManager;
    }
}
