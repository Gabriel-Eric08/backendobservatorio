package com.setd.backendobservatorio.api.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.setd.backendobservatorio.domain.model.DataSet;
import com.setd.backendobservatorio.usecase.FindAllDataSetUseCase;
import com.setd.backendobservatorio.usecase.GetDataSetByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/dataset")
public class DataSetController {

    // Config pra o usecase e o caminho para salvar os csvs do balacobaco
    private final CreateDataSetUseCase createDataSetUseCase;
    private final GetDataSetByIdUseCase getDataSetByIdUseCase;
    private final Path fileStorageLocation;
    private final FindAllDataSetUseCase findAllDataSetUseCase;
    
    public DataSetController(CreateDataSetUseCase createDataSetUseCase, FileStorageProperties fileStorageProperties, FindAllDataSetUseCase findAllDataSetUseCase, GetDataSetByIdUseCase getDataSetByIdUseCase){
        this.createDataSetUseCase=createDataSetUseCase;
        this.getDataSetByIdUseCase = getDataSetByIdUseCase;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.findAllDataSetUseCase = findAllDataSetUseCase;
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

    @GetMapping("/all")
    public ResponseEntity<String> findAll(){
        List<DataSet> dataSets = findAllDataSetUseCase.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataSets);
            return ResponseEntity.ok(jsonResult);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Erro ao serializar os dados");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable long id, HttpServletRequest request){
        DataSet dataSet = getDataSetByIdUseCase.getById(id);
        try{
        Path path = Paths.get(dataSet.getUrl()).normalize();
        Resource resource = new UrlResource(path.toUri());
            
        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        }catch(MalformedURLException e){
                return ResponseEntity.badRequest().build();
        }
        }
        @GetMapping("/all")
    public ResponseEntity<String> findAll(){
        List<DataSet> dataSets = findAllDataSetUseCase.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataSets);
            return ResponseEntity.ok(jsonResult);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Erro ao serializar os dados");
        }
    }
    }