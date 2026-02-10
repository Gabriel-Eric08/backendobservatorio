package com.setd.backendobservatorio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setd.backendobservatorio.api.dto.ChangeStatusRequest;
import com.setd.backendobservatorio.usecase.ExistsByNomeUseCase;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ExistsByNomeUseCase existsByNomeUseCase;
    public TestController(ExistsByNomeUseCase existsByNomeUseCase){
        this.existsByNomeUseCase=existsByNomeUseCase;
    }

    @PostMapping("/change")
    public ResponseEntity<String> change(@RequestBody ChangeStatusRequest request){
        String nome = request.getNome();
        boolean exists = existsByNomeUseCase.existsByNome(nome);

        if(exists){
            return ResponseEntity.ok("EXISTE");
        }else{
            return ResponseEntity.ok("NÃO EXISTE");
        }
    }
}
