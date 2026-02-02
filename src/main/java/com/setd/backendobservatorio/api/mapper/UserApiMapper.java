package com.setd.backendobservatorio.api.mapper;

import com.setd.backendobservatorio.api.dto.CreateUserRequest;
import com.setd.backendobservatorio.usecase.dto.CreateUserInput;


public class UserApiMapper {
    public static CreateUserInput toCreateUserInput(CreateUserRequest request) {
        return new CreateUserInput(request.getNome(), request.getEmail(), request.getSenha(), request.getRoleId());
    }
}
