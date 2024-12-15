package com.example.demo.service;

import com.example.demo.model.dto.LogDto;
import com.example.demo.model.entity.LogTransaksi;
import com.example.demo.repository.LogRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LogServiceImpl implements ILogService{

    @Autowired
    private LogRespository logRespository;
    @Override
    public ResponseEntity<String> saveToLog(LogDto logDto) {
        LogTransaksi logTransaksi = new LogTransaksi();
        logTransaksi.setCsvFilename(logDto.getCsvFilename());
        logTransaksi.setTotalRecord(logDto.getTotalRecord());
        logTransaksi.setTotalRecordFailed(logDto.getTotalRecordFailed());
        logTransaksi.setTotalRecordSuccess(logDto.getTotalRecordSuccess());
        logRespository.save(logTransaksi);
        return ResponseEntity.ok("Log saved successfully");
    }
}
