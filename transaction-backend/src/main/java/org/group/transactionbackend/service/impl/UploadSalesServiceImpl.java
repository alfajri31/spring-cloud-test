package org.group.transactionbackend.service.impl;

import org.group.transactionbackend.model.dto.LogDto;
import org.group.transactionbackend.model.entity.Fee;
import org.group.transactionbackend.model.entity.Transaksi;
import org.group.transactionbackend.repository.FeeRepository;
import org.group.transactionbackend.repository.TransaksiRepository;
import org.group.transactionbackend.service.ILogTransactionClient;
import org.group.transactionbackend.service.IUploadSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadSalesServiceImpl implements IUploadSales {

    private final TransaksiRepository transaksiRepository;
    private final FeeRepository feeRepository;

    @Autowired
    private ILogTransactionClient iLogTransactionClient;

    UploadSalesServiceImpl(TransaksiRepository transaksiRepository,FeeRepository feeRepository) {
        this.transaksiRepository = transaksiRepository;
        this.feeRepository = feeRepository;
    }

    @Override
    public void saveCsvData(MultipartFile file, List<Integer> employeeIds, Integer managerId) {
        // Delete all transactions before processing the CSV data
        transaksiRepository.deleteAll();
        List<Transaksi> transaksiList = new ArrayList<>();
        long totalAmount = 0L;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) {
                    throw new IllegalArgumentException("Invalid CSV format: insufficient columns.");
                }

                Transaksi transaksi = parseTransaksi(data);
                transaksiList.add(transaksi);

                if (employeeIds.contains(transaksi.getEmployeeId())) {
                    totalAmount += transaksi.getAmount();
                }
            }

            // Save transactions to the database
            transaksiRepository.saveAllAndFlush(transaksiList);

            //sent to log
            LogDto logDto = new LogDto();
            logDto.setCsvFilename(file.getOriginalFilename());
            logDto.setTotalRecord(transaksiList.size());
            logDto.setTotalRecordFailed(0);
            logDto.setTotalRecordSuccess(transaksiList.size());
            iLogTransactionClient.postLogTransaction(logDto);

            // Save manager's fee to the database
            saveManagerFee(managerId, totalAmount);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process CSV file: " + e.getMessage(), e);
        }

        ResponseEntity.ok().body("CSV data processed successfully.");
    }

    private Transaksi parseTransaksi(String[] data) {
        Transaksi transaksi = new Transaksi();
        try {
            transaksi.setEmployeeId(Integer.parseInt(data[0].trim()));
            transaksi.setAmount(Long.parseLong(data[5].trim()));
            LocalDateTime localDateTime = LocalDateTime.parse(
                    data[4].trim() + " 00:00",
                    DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
            );
            transaksi.setTglTransaksi(localDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing CSV row: " + String.join(",", data), e);
        }
        return transaksi;
    }

    private void saveManagerFee(Integer managerId, long totalAmount) {
        Fee fee = feeRepository.findById(managerId).orElse(new Fee());
        fee.setEmployeeId(managerId);
        fee.setTglFee(LocalDateTime.now());
        fee.setAmountFee(totalAmount);
        feeRepository.saveAndFlush(fee);
    }


}
