package com.setd.backendobservatorio.domain.gateways;

import com.setd.backendobservatorio.domain.model.DataSetTable;

public interface CsvParserGateway {
    DataSetTable convert(String path);
}
