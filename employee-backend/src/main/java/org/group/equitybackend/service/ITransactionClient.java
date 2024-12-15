package org.group.equitybackend.service;

import org.group.equitybackend.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITransactionClient {
    ResponseEntity<?> postTransaction(MultipartFile file, UserDto userDto) throws IOException;
}
