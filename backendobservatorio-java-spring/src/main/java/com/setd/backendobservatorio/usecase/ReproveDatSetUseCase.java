package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.repository.DataSetRepository;

@Service
public class ReproveDatSetUseCase {
    private final DataSetRepository dataSetRepository;

    public ReproveDatSetUseCase(DataSetRepository dataSetRepository){
        this.dataSetRepository=dataSetRepository;
    }

    public boolean reprove(long id){
        return dataSetRepository.reprove(id);
    }
}
