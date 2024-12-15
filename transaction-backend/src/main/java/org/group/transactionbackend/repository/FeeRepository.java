package org.group.transactionbackend.repository;

import org.group.transactionbackend.model.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee,Integer> {
}
