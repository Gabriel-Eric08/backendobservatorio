package com.setd.backendobservatorio.usecase.dto;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
public class CreateDataSetInput {
    private String titulo;
    private String tema;
    private String orgao;
    private String contato;
    private String descricao;
    private YearMonth periodo_inicial;
    private YearMonth periodo_final;
}
