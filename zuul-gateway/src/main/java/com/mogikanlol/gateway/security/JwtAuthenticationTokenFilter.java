 package com.mogikanlol.gateway.security;

 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import lombok.RequiredArgsConstructor;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.web.filter.OncePerRequestFilter;

 import java.io.IOException;

 @RequiredArgsConstructor
 public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

     private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
     private static final String TOKEN_PREFIX = "Bearer ";

     private final JwtTokenProvider tokenProvider;
     private final UserDetailsService userDetailsService;

     @Override
     protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws IOException, ServletException {
         String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
         if (header != null && header.startsWith(TOKEN_PREFIX)) {
             String token = header.replace(TOKEN_PREFIX, "");
             String username = tokenProvider.getUsernameFromToken(token);

             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                 UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                         userDetails,
                         null,
                         userDetails.getAuthorities());
                 SecurityContextHolder.getContext().setAuthentication(authentication);
             }
         }
         filterChain.doFilter(request, response);
     }
 }
