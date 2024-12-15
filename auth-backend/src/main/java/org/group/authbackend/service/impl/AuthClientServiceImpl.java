package org.group.authbackend.service.impl;

import org.group.authbackend.model.dto.CsrfTokenDto;
import org.group.authbackend.model.dto.LoginDto;
import org.group.authbackend.model.dto.UserDto;
import org.group.authbackend.service.AuthClient;
import org.group.authbackend.util.AuthProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthClientServiceImpl implements AuthClient {
    private final AuthProperties authProperties;
    private final RestTemplate restTemplate;

    public AuthClientServiceImpl(AuthProperties authProperties,RestTemplate restTemplate) {
        this.authProperties = authProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Object> getLogin(LoginDto loginDto) {
        ResponseEntity<CsrfTokenDto> csrfTokenDtoResponseEntity = getCsrfToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CSRF-TOKEN", csrfTokenDtoResponseEntity.getBody().token);
        HttpEntity<LoginDto> entity = new HttpEntity<>(loginDto,headers);
        ResponseEntity<UserDto> response = restTemplate.exchange(authProperties.getLogin(),
                HttpMethod.POST,
                entity,
                UserDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            headers.set("token", response.getBody().getToken());
            return ResponseEntity.ok().headers(headers).body(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    @Override
    public ResponseEntity<CsrfTokenDto> getCsrfToken() {
        ResponseEntity<CsrfTokenDto> response = restTemplate.getForEntity(authProperties.getCsrf(), CsrfTokenDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok().body(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
}
