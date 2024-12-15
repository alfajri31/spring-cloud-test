package org.group.transactionbackend.controller;

import org.group.transactionbackend.repository.FeeRepository;
import org.group.transactionbackend.service.IUploadSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private IUploadSales uploadSales;
    @Autowired
    private FeeRepository feeRepository;

    @PostMapping("upload")
    public void postData(
             MultipartFile file,
             String employeeIds,
             String managerId) {List<Integer> employeeIdList = Arrays.stream(employeeIds.split(";"))
                    .map(Integer::parseInt)
                    .toList();
            uploadSales.saveCsvData(file,employeeIdList,Integer.parseInt(managerId));
    }
}
