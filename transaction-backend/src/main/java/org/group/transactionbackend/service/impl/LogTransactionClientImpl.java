package org.group.transactionbackend.service.impl;

import lombok.AllArgsConstructor;
import org.group.transactionbackend.model.dto.LogDto;
import org.group.transactionbackend.service.ILogTransactionClient;
import org.group.transactionbackend.util.LogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@AllArgsConstructor
public class LogTransactionClientImpl implements ILogTransactionClient {

    @Autowired
    private LogProperties logProperties;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void postLogTransaction(LogDto logDto) throws IOException {
        ResponseEntity<Object> response = restTemplate.postForEntity(
                logProperties.postLog(),
                logDto,
                Object.class);
        ResponseEntity.status(200).body(response);
    }
}
