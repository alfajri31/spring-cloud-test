package org.group.transactionbackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_fee")
public class Fee {
    @Id
    public Integer employeeId;
    public Long amountFee;
    public LocalDateTime tglFee;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(Long amountFee) {
        this.amountFee = amountFee;
    }

    public LocalDateTime getTglFee() {
        return tglFee;
    }

    public void setTglFee(LocalDateTime tglFee) {
        this.tglFee = tglFee;
    }

}
