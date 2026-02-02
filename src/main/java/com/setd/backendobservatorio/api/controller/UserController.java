package com.setd.backendobservatorio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setd.backendobservatorio.api.dto.CreateUserRequest;
import com.setd.backendobservatorio.api.mapper.UserApiMapper;
import com.setd.backendobservatorio.domain.model.User;
import com.setd.backendobservatorio.usecase.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User user = createUserUseCase.save(UserApiMapper.toCreateUserInput(request));
        return ResponseEntity.ok(user);
    }
}
