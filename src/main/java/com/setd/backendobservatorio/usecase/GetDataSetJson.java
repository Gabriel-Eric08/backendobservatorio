package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;
import com.setd.backendobservatorio.domain.model.DataSetTable;
import com.setd.backendobservatorio.infrastructure.gatewas.CsvParserService;

@Service
public class GetDataSetJson {
    private final CsvParserService csvParserService;

    public GetDataSetJson(CsvParserService csvParserService){
        this.csvParserService = csvParserService;
    }

    public DataSetTable convert(String stringPath){
        return csvParserService.convert(stringPath);
    }
}
