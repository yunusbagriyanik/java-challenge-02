package com.java.challenge.hcase02.repository;

import com.java.challenge.hcase02.entity.DataModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataModelRepository extends JpaRepository<DataModelEntity, Long> {
}