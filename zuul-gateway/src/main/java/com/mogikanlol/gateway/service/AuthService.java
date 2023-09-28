// package com.mogikanlol.gateway.service;

// import com.mogikanlol.gateway.dto.SigninRequest;
// import com.mogikanlol.gateway.dto.SignupRequest;
// import com.mogikanlol.gateway.security.JwtTokenProvider;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// @RequiredArgsConstructor
// @Service
// public class AuthService {

//     private final JwtTokenProvider jwtTokenProvider;
//     private final UserService userService;
//     private final AuthenticationManager authenticationManager;

//     public String register(SignupRequest signupRequest) {
//         userService.create(signupRequest.getUsername(), signupRequest.getPassword());
//         return jwtTokenProvider.generateToken(signupRequest.getUsername());
//     }

//     public String authenticate(SigninRequest signinRequest) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         signinRequest.getUsername(),
//                         signinRequest.getPassword()));

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         return jwtTokenProvider.generateToken(signinRequest.getUsername());
//     }
// }
