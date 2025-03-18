package com.animalnovels.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@Component
public class RedirectDebugFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RedirectDebugFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        logger.info("Request to URI: {}", requestURI);
        
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            @Override
            public void sendRedirect(String location) throws IOException {
                logger.info("Redirecting from {} to {}", requestURI, location);
                super.sendRedirect(location);
            }
        };
        
        filterChain.doFilter(request, responseWrapper);
    }
}