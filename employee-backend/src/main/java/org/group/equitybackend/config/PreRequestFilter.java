package org.group.equitybackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.group.equitybackend.model.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;

@Component
public class PreRequestFilter extends OncePerRequestFilter {

    private Key secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String secret = "nwQKsTvCuISOOjIHPzWmJkamO/A4FphpMzjuijvK5XE=";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String token = authorizationHeader.substring(6); // Remove "Bearer " prefix
            try {
                    UserDto userDto = new UserDto();
                    Claims claims = Jwts.parser()
                            .setSigningKey(secret)
                            .parseClaimsJws(token)
                            .getBody();
                    // Extract claims
                    String subject = claims.getSubject();
                    userDto.setUsername(subject);
                    request.setAttribute("user",userDto);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
