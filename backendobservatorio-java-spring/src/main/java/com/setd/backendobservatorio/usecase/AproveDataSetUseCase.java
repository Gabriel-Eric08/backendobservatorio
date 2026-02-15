package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.repository.DataSetRepository;

@Service
public class AproveDataSetUseCase {
    private final DataSetRepository dataSetRepository;

    public AproveDataSetUseCase(DataSetRepository dataSetRepository){
        this.dataSetRepository=dataSetRepository;
    }
    public boolean aprove(long id){
        return dataSetRepository.aprove(id);
    }
}
