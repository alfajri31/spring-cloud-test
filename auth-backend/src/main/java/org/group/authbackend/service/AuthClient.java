package org.group.authbackend.service;

import org.group.authbackend.model.dto.CsrfTokenDto;
import org.group.authbackend.model.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthClient {
    ResponseEntity<Object> getLogin(@RequestBody LoginDto loginDto);
    ResponseEntity<CsrfTokenDto> getCsrfToken();
}
