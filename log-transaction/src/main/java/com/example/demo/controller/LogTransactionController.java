package com.example.demo.controller;

import com.example.demo.model.dto.LogDto;
import com.example.demo.service.ILogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/log")
@AllArgsConstructor
public class LogTransactionController {

    @Autowired
    private ILogService iLogService;

    @PostMapping("transaction")
    public ResponseEntity<?> logData(@RequestBody LogDto logDto, HttpServletRequest request) throws IOException {
        Object result = iLogService.saveToLog(logDto);
        return ResponseEntity.ok(result);
    }
}
