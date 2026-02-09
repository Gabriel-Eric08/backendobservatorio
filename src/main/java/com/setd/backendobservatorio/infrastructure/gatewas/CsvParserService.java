package com.setd.backendobservatorio.infrastructure.gatewas;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.setd.backendobservatorio.domain.gateways.CsvParserGateway;
import com.setd.backendobservatorio.domain.model.DataSetTable;

@Service
public class CsvParserService implements CsvParserGateway {
    @Override
    public DataSetTable convert(String stringPath){
        Path path = Path.of(stringPath);
        DataSetTable table = new DataSetTable();
        try( Reader reader = Files.newBufferedReader(path);
             CSVReader csvReader = new CSVReaderBuilder(reader).build();
            ){
                List<String[]> allLines = csvReader.readAll();
                String[] headers = allLines.get(0);
                List<String[]> rows = new ArrayList<>();
                for(int i = 1; i < allLines.size()-1; i++){
                    if(allLines.get(i) != null ){
                        rows.add(allLines.get(i));
                    }else{
                        continue;
                    }
                }
                table.setHeaders(headers);
                table.setRows(rows);
                
            } catch (Exception e) {
                throw new RuntimeException("Error parsing CSV file", e);
            }
            return table;
    }
}
