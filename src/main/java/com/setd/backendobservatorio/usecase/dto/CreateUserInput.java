package com.setd.backendobservatorio.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {
    private String nome;
    private String email;
    private String senha;
    private int roleId;
}
