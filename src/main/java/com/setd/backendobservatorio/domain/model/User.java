package com.setd.backendobservatorio.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private int roleId;
    private LocalDate createdAt;

    public User(String nome, String email, String senha, int roleId) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.roleId = roleId;
        this.createdAt = LocalDate.now();
    }
}
