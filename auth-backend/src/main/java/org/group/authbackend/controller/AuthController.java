package org.group.authbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.group.authbackend.model.dto.LoginDto;
import org.group.authbackend.service.AuthClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthClient authClient;

    AuthController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> toLogin(@RequestBody LoginDto loginDto) {
        return authClient.getLogin(loginDto);
    }
}
