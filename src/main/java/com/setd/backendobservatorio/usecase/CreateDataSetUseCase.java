package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.domain.repository.DataSetRepository;
import com.setd.backendobservatorio.usecase.dto.CreateDataSetInput;

@Service
public class CreateDataSetUseCase {
    private final DataSetRepository dataSetRepository;

    public CreateDataSetUseCase(DataSetRepository dataSetRepository){
        this.dataSetRepository = dataSetRepository;
    }

    public DataSet save(CreateDataSetInput input){
        DataSet dataSet = new DataSet(
            input.getTitulo(),
            input.getTema(),
            input.getOrgao(),
            input.getContato(),
            input.getDescricao(),
            input.getUrl(),
            input.getPeriodo_inicial(),
            input.getPeriodo_final()
        );
        dataSetRepository.save(dataSet);
        return dataSet;
    }
}
