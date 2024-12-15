package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log_transaction") // Replace with the actual table name
public class LogTransaksi {
    @Id
    @Column(name = "csv_filename", nullable = false, length = 255) // Adjust length based on your VARCHAR size
    private String csvFilename;

    @Column(name = "total_record", nullable = true)
    private Integer totalRecord;

    @Column(name = "total_record_failed", nullable = true)
    private Integer totalRecordFailed;

    @Column(name = "total_record_success", nullable = true)
    private Integer totalRecordSuccess;

    public String getCsvFilename() {
        return csvFilename;
    }

    public void setCsvFilename(String csvFilename) {
        this.csvFilename = csvFilename;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalRecordFailed() {
        return totalRecordFailed;
    }

    public void setTotalRecordFailed(Integer totalRecordFailed) {
        this.totalRecordFailed = totalRecordFailed;
    }

    public Integer getTotalRecordSuccess() {
        return totalRecordSuccess;
    }

    public void setTotalRecordSuccess(Integer totalRecordSuccess) {
        this.totalRecordSuccess = totalRecordSuccess;
    }
}
