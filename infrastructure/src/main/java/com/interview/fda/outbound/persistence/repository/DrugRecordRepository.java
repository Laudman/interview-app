package com.interview.fda.outbound.persistence.repository;

import com.interview.fda.outbound.persistence.entity.DrugRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRecordRepository extends JpaRepository<DrugRecordEntity, String> {
}
