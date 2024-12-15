package org.group.equitybackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.group.equitybackend.model.dto.UserDto;
import org.group.equitybackend.service.ITransactionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api_fe")
@AllArgsConstructor
public class PostDataController {

    @Autowired
    private ITransactionClient iTransactionClient;

    @PostMapping("post_data")
    public ResponseEntity<?> postData(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        UserDto user = (UserDto) request.getAttribute("user");
        return iTransactionClient.postTransaction(file,user);
    }
}
