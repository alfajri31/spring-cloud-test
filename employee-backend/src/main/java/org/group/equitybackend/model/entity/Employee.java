package org.group.equitybackend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_employee") // Sesuai dengan nama tabel di database
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Menyesuaikan tipe ID yang otomatis bertambah
    @Column(name = "employee_id", nullable = false)
    public Integer employeeId;

    @Column(name = "employee_name", length = 50) // Membatasi panjang sesuai dengan VARCHAR(50)
    private String employeeName;

    @Column(name = "employee_manager_id")
    private Integer employeeManagerId;

    // Getters and Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeManagerId() {
        return employeeManagerId;
    }

    public void setEmployeeManagerId(Integer employeeManagerId) {
        this.employeeManagerId = employeeManagerId;
    }

}
