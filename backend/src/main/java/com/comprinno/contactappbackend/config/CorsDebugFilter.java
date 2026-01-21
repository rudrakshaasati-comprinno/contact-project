package com.comprinno.contactappbackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CorsDebugFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("🟣 CORS-FILTER HIT");
        System.out.println("🟣 Method = " + request.getMethod());
        System.out.println("🟣 URI = " + request.getRequestURI());
        System.out.println("🟣 Origin = " + request.getHeader("Origin"));
        System.out.println("🟣 Access-Control-Request-Method = "
                + request.getHeader("Access-Control-Request-Method"));

        filterChain.doFilter(request, response);
    }
}