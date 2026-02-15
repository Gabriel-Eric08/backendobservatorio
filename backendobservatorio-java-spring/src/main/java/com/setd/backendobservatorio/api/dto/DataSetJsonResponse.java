package com.setd.backendobservatorio.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DataSetJsonResponse {
    private String titulo;
    private String tema;
    private String orgao;
    private String contato;
    private String descricao;
    private String periodo_incial;
    private String periodo_final;
    private String[] headers;
    private List<String[]> rows;
}