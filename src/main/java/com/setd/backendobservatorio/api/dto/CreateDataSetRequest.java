package com.setd.backendobservatorio.api.dto;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
@RequiredArgsConstructor
public class CreateDataSetRequest {
    private String titulo;
    private String tema;
    private String orgao;
    private String contato;
    private String descricao;
    private String url;
    private YearMonth periodo_inicial;
    private YearMonth periodo_final;
}
