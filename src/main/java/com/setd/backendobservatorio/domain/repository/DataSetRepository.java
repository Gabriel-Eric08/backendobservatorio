package com.setd.backendobservatorio.domain.repository;
import com.setd.backendobservatorio.domain.model.DataSet;

public interface DataSetRepository {
    DataSet save(DataSet dataSet);
    List<DataSet> findAll();
    DataSet getById(long id);
}
