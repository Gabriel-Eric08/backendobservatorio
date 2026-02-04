package com.setd.backendobservatorio.domain.model;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class DataSet {
    private long id;
    private String titulo;
    private String tema;
    private String orgao;
    private String contato;
    private String descricao;
    private String url;
    private YearMonth periodo_inicial;
    private YearMonth periodo_final;

    public DataSet(String titulo, String tema, String orgao, String contato, String descricao, String url, YearMonth periodo_inicial, YearMonth periodo_final){
        this.titulo = titulo;
        this.tema = tema;
        this.orgao = orgao;
        this.contato = contato;
        this.descricao = descricao;
        this.url = url;
        this.periodo_inicial = periodo_inicial;
        this.periodo_final = periodo_final;
    }

     public boolean isEmpty() {
        return (titulo == null || titulo.isBlank())
            && (tema == null || tema.isBlank())
            && (orgao == null || orgao.isBlank())
            && (contato == null || contato.isBlank())
            && (descricao == null || descricao.isBlank())
            && (url == null || url.isBlank())
            && (periodo_inicial == null)
            && (periodo_final == null);
    }
}
