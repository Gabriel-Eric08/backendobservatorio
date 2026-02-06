package com.setd.backendobservatorio.infrastructure.persistence.adapter;

import java.util.List;

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
    public List<DataSet> findAll(){
        List<DataSetEntity> dataSetEntities = jpaDataSetRepository.findAll();
        List<DataSet> dataSets;
        dataSets = dataSetEntities.stream().map(entity -> {
            DataSet dataSet = new DataSet();
            dataSet.setId(entity.getId());
            dataSet.setTitulo(entity.getTitulo());
            dataSet.setTema(entity.getTema());
            dataSet.setOrgao(entity.getOrgao());
            dataSet.setContato(entity.getContato());
            dataSet.setDescricao(entity.getDescricao());
            dataSet.setUrl(entity.getUrl());
            dataSet.setPeriodo_inicial(entity.getPeriodo_inicial());
            dataSet.setPeriodo_final(entity.getPeriodo_final());
            return dataSet;
        }).toList();
        return dataSets;
    }
}
