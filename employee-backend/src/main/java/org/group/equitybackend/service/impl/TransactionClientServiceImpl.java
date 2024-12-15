package org.group.equitybackend.service.impl;

import org.group.equitybackend.model.dto.UserDto;
import org.group.equitybackend.model.entity.Employee;
import org.group.equitybackend.repository.EmployeeRepository;
import org.group.equitybackend.service.ITransactionClient;
import org.group.equitybackend.util.TransactionsProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionClientServiceImpl implements ITransactionClient {

    private final TransactionsProperties transactionsProperties;
    private final RestTemplate restTemplate;

    private final EmployeeRepository employeeRepository;

    public TransactionClientServiceImpl (TransactionsProperties transactionsProperties, RestTemplate restTemplate,EmployeeRepository employeeRepository) {
        this.transactionsProperties = transactionsProperties;
        this.restTemplate = restTemplate;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<?> postTransaction(MultipartFile file, UserDto userDto) {
        List<Employee> employees = employeeRepository.findAllByManagerId(Long.parseLong(userDto.getUsername()));
        String employeeIdsString = employees.stream()
                .map(Employee::getEmployeeId)
                .map(String::valueOf)
                .collect(Collectors.joining(";"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("employeeIds", employeeIdsString);
        body.add("managerId", userDto.getUsername());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity(
                transactionsProperties.postUpload(),
                requestEntity,
                Object.class);
        return ResponseEntity.status(200).body(response);
    }

}
