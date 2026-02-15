package com.setd.backendobservatorio.api.mapper;

import com.setd.backendobservatorio.api.dto.DataSetJsonResponse;
import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.domain.model.DataSetTable;
import com.setd.backendobservatorio.infrastructure.persistence.utils.YearMonthConverter;

public class DataSetTableApiMapper {
    public static DataSetJsonResponse dataSetTableConvert(DataSet dataSet, DataSetTable table){
        YearMonthConverter conversor = new YearMonthConverter();

        DataSetJsonResponse response = new DataSetJsonResponse();

        response.setContato(dataSet.getContato());
        response.setDescricao(dataSet.getDescricao());
        response.setOrgao(dataSet.getOrgao());
        response.setTema(dataSet.getTema());
        response.setTitulo(dataSet.getTitulo());
        response.setPeriodo_incial(conversor.convertToDatabaseColumn(dataSet.getPeriodo_inicial()));
        response.setPeriodo_final(conversor.convertToDatabaseColumn(dataSet.getPeriodo_final()));

        response.setHeaders (table.getHeaders());
        response.setRows(table.getRows());
        
        return response;
    }
}
