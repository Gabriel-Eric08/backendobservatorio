package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.model.User;
import com.setd.backendobservatorio.domain.repository.UserRepository;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User login(String nome, String senha){
        User user = userRepository.findByNomeAndSenha(nome, senha);
        return user;
    }
}
