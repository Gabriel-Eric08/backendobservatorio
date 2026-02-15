package com.setd.backendobservatorio.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter@Setter
public class CreateUserRequest {
    private String nome;
    private String email;
    private String senha;
    private int roleId;
}
