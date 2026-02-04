package com.setd.backendobservatorio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setd.backendobservatorio.infrastructure.persistence.entity.DataSetEntity;

public interface JpaDataSetRepository extends JpaRepository<DataSetEntity, Long> {
    DataSetEntity save(DataSetEntity dataSet);
    DataSetEntity getById(long id);
}
