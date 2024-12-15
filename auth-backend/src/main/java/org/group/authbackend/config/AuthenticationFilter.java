package org.group.authbackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.group.authbackend.model.dto.UserDto;
import org.group.authbackend.model.entity.User;
import org.group.authbackend.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Environment environment;

    private final UserRepository userRepository;

    AuthenticationFilter(Environment environment,UserRepository userRepository) {
        this.environment = environment;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto cred = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(cred.getUsername(),cred.getPassword(),new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Date issueDate = new Date(System.currentTimeMillis() + Long.parseLong("3600000"));
        Date expiration = new Date(System.currentTimeMillis() + Long.parseLong("1890777600"));
        String userName = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        User userOptional = userRepository.findByUsername(userName);
        String token = Jwts.builder()
                .setSubject(userOptional.getId())
                .setIssuer("equity")
                .setIssuedAt(issueDate)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "nwQKsTvCuISOOjIHPzWmJkamO/A4FphpMzjuijvK5XE=")
                .compact();
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed: " + exception.getMessage());
    }
}
