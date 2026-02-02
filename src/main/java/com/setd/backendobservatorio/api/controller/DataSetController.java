package com.setd.backendobservatorio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setd.backendobservatorio.api.dto.CreateDataSetRequest;
import com.setd.backendobservatorio.api.mapper.DataSetApiMapper;
import com.setd.backendobservatorio.usecase.CreateDataSetUseCase;

@RestController
@RequestMapping("/dataset")
public class DataSetController {
    private final CreateDataSetUseCase createDataSetUseCase;

    public DataSetController(CreateDataSetUseCase createDataSetUseCase){
        this.createDataSetUseCase=createDataSetUseCase;
    }
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody CreateDataSetRequest request){
        createDataSetUseCase.save((DataSetApiMapper.toInput(request)));
        return ResponseEntity.ok("DataSet registrado com sucesso!");
    }
}
