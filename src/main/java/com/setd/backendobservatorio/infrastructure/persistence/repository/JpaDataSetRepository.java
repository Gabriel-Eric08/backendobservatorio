package com.setd.backendobservatorio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.infrastructure.persistence.entity.DataSetEntity;

public interface JpaDataSetRepository extends JpaRepository<DataSetEntity, Long> {
    DataSet save(DataSet dataSet);
}
