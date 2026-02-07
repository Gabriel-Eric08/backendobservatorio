package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.domain.repository.DataSetRepository;

@Service
public class GetDataSetByIdUseCase {
    private final DataSetRepository dataSetRepository;
    public GetDataSetByIdUseCase(DataSetRepository dataSetRepository){
        this.dataSetRepository = dataSetRepository;
    }
    public DataSet getById(long id){
        return dataSetRepository.getById(id);
    }
}
