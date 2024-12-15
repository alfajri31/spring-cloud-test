package org.group.transactionbackend.repository;

import org.group.transactionbackend.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi,Integer> {
}
