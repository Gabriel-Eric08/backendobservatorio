package com.setd.backendobservatorio.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.domain.repository.DataSetRepository;

@Service
public class FindAllDataSetUseCase {

    private final DataSetRepository dataSetRepository;

    public FindAllDataSetUseCase(DataSetRepository dataSetRepository) {
        this.dataSetRepository = dataSetRepository;
    }

    public List<DataSet> findAll() {
        return dataSetRepository.findAll();
    }
}
