package com.setd.backendobservatorio.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;
import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.domain.repository.DataSetRepository;
import com.setd.backendobservatorio.infrastructure.persistence.entity.DataSetEntity;
import com.setd.backendobservatorio.infrastructure.persistence.repository.JpaDataSetRepository;

@Component
public class DataSetRepositoryAdapter implements DataSetRepository{
    private final JpaDataSetRepository jpaDataSetRepository;

    public DataSetRepositoryAdapter(JpaDataSetRepository jpaDataSetRepository){
        this.jpaDataSetRepository = jpaDataSetRepository;
    }

    @Override
    public DataSet save(DataSet dataSet){
        DataSetEntity dataSetEntity = new DataSetEntity();
        dataSetEntity.setId(dataSet.getId());
        dataSetEntity.setTitulo(dataSet.getTitulo());
        dataSetEntity.setTema(dataSet.getTema());
        dataSetEntity.setOrgao(dataSet.getOrgao());
        dataSetEntity.setContato(dataSet.getContato());
        dataSetEntity.setDescricao(dataSet.getDescricao());
        dataSetEntity.setUrl(dataSet.getUrl());
        dataSetEntity.setPeriodo_inicial(dataSet.getPeriodo_inicial());
        dataSetEntity.setPeriodo_final(dataSet.getPeriodo_final());
        jpaDataSetRepository.save(dataSetEntity);
        return dataSet;
    }

    @Override
    public DataSet getById(long id){
        DataSetEntity dataSetEntity = jpaDataSetRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado!"));
        DataSet dataSet = new DataSet(dataSetEntity.getTitulo(),dataSetEntity.getTema(),dataSetEntity.getOrgao(),dataSetEntity.getContato(),dataSetEntity.getDescricao(),dataSetEntity.getUrl(),dataSetEntity.getPeriodo_inicial(),dataSetEntity.getPeriodo_final()); 
        return dataSet;
        
    }
}
