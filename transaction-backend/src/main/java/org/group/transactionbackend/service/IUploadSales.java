package org.group.transactionbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadSales {
    void saveCsvData(MultipartFile multipartFile, List<Integer> employeeIds, Integer managerId);
}
