package com.setd.backendobservatorio.api.dto;

import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth periodo_inicial;
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth periodo_final;
}
