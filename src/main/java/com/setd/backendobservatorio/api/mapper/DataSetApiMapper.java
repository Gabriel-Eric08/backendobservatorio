package com.setd.backendobservatorio.api.mapper;

import com.setd.backendobservatorio.api.dto.CreateDataSetRequest;
import com.setd.backendobservatorio.usecase.dto.CreateDataSetInput;

public class DataSetApiMapper {
    public static CreateDataSetInput toInput(CreateDataSetRequest request){
        CreateDataSetInput createDataSetInput = new CreateDataSetInput(request.getTitulo(),request.getTema(), request.getOrgao(), request.getContato(), request.getDescricao(), request.getUrl(), request.getPeriodo_inicial(), request.getPeriodo_final());
        return createDataSetInput;
    }
}
