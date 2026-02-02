package com.setd.backendobservatorio.infrastructure.persistence.entity;

import java.time.YearMonth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dataset")
public class DataSetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String tema;

    @Column(nullable = false)
    private String orgao;

    @Column(nullable = false)
    private String contato;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private YearMonth periodo_inicial;

    @Column(nullable = false)
    private YearMonth periodo_final;
}
