package com.uasd.sgb.repository;

import com.uasd.sgb.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity,Long> {
    long countByUserEntity_Id(Long id);
}
