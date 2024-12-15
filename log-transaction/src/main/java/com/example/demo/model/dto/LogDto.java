package com.example.demo.model.dto;

import lombok.Data;

@Data
public class LogDto {
    public String csvFilename;
    public Integer totalRecord;
    public Integer totalRecordFailed;

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

    public Integer totalRecordSuccess;

}

