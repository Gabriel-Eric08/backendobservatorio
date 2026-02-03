package com.setd.backendobservatorio.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.setd.backendobservatorio.api.dto.CreateDataSetRequest;
import com.setd.backendobservatorio.api.mapper.DataSetApiMapper;
import com.setd.backendobservatorio.config.FileStorageProperties;
import com.setd.backendobservatorio.infrastructure.persistence.utils.YearMonthConverter;
import com.setd.backendobservatorio.usecase.CreateDataSetUseCase;


@RestController
@RequestMapping("/dataset")
public class DataSetController {

    // Config pra o usecase e o caminho para salvar os csvs do balacobaco
    private final CreateDataSetUseCase createDataSetUseCase;
    private final Path fileStorageLocation;
    
    public DataSetController(CreateDataSetUseCase createDataSetUseCase, FileStorageProperties fileStorageProperties){
        this.createDataSetUseCase=createDataSetUseCase;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    // Anotação para receber multipart/form-data (Receber arquivo e json juntos, no postman não pra colocar um campo "file" e outro "data" com o json)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> create(@RequestPart("file") MultipartFile file, @RequestPart("data") String dataJson){

        // Procurando bug no mapeamento do json para o CreateDataSetRequest
        System.out.println("DATA JSON RECEBIDO:");
        System.out.println(dataJson);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        CreateDataSetRequest request;
        try {
            request = mapper.readValue(dataJson, CreateDataSetRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("JSON inválido");
        }


        String filename = request.getTitulo() + new YearMonthConverter().convertToDatabaseColumn(request.getPeriodo_inicial()) + new YearMonthConverter().convertToDatabaseColumn(request.getPeriodo_final()) + ".csv";
        Path target = fileStorageLocation.resolve(filename);
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio");
            }else if(!file.getOriginalFilename().toLowerCase().endsWith(".csv")){
                return ResponseEntity.badRequest().body("Tipo de arquivo inválido. Apenas arquivos CSV são permitidos.");
            }
            else{
                file.transferTo(target);
                createDataSetUseCase.save((DataSetApiMapper.toInput(request, target.toString())));
                return ResponseEntity.ok("DataSet registrado com sucesso!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo", e);
        }
    }
}
