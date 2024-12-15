package org.group.authbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.group.authbackend.model.dto.CsrfTokenDto;
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
    public ResponseEntity<CsrfTokenDto> csrf(HttpServletRequest request) {
        CsrfToken csrfToken= (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        CsrfTokenDto csrfTokenDto = new CsrfTokenDto();
        csrfTokenDto.setToken(csrfToken.getToken());
        csrfTokenDto.setHeaderName(csrfToken.getHeaderName());
        csrfTokenDto.setParameterName(csrfToken.getParameterName());
//        CsrfTokenDto csrfTokenDto = new CsrfTokenDto();
        return ResponseEntity.ok(csrfTokenDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> toLogin(@RequestBody LoginDto loginDto) {
        return authClient.getLogin(loginDto);
    }
}
