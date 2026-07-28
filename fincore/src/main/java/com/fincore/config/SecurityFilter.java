package com.fincore.config;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fincore.entities.User;
import com.fincore.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private TokenConfig tokenConfig;
    private final UserRepository userRepository;

    public SecurityFilter(TokenConfig tokenConfig, UserRepository userRepository) {
        this.tokenConfig = tokenConfig;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizedHeader = request.getHeader("Authorization");
        if (Strings.isNotEmpty(authorizedHeader) && authorizedHeader.startsWith("Bearer ")) {
            String token = authorizedHeader.substring("Bearer ".length());
            
            Optional<JWTUserData> optUser = tokenConfig.validateToken(token);
            if (optUser.isPresent()){
                JWTUserData userData = optUser.get();

                User user = userRepository
                        .findByUsername(userData.username())
                        .orElseThrow();

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }else{
            filterChain.doFilter(request, response);
        }
    }
        
        
           
}
            
            