package com.example.demo.repository;

import com.example.demo.model.entity.LogTransaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRespository extends JpaRepository<LogTransaksi,String > {
}
