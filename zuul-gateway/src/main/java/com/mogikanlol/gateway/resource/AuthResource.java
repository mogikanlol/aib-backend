// package com.mogikanlol.gateway.resource;

// import com.mogikanlol.gateway.dto.SigninRequest;
// import com.mogikanlol.gateway.dto.SignupRequest;
// import com.mogikanlol.gateway.service.AuthService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// import javax.validation.Valid;

// @RequiredArgsConstructor
// @RestController
// public class AuthResource {

//     private final AuthService authService;

//     @GetMapping("/test")
//     public String test() {
//         return "String test";
//     }

//     @PostMapping("/auth/login")
//     public String login(@RequestBody @Valid SigninRequest signinRequest) {
//         return authService.authenticate(signinRequest);
//     }

//     @PostMapping("/auth/register")
//     public String register(@RequestBody @Valid SignupRequest signupRequest) {
//         return authService.register(signupRequest);
//     }

// }
