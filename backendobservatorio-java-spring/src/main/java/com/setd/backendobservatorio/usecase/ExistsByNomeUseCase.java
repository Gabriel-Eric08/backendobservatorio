package com.setd.backendobservatorio.usecase;

import org.springframework.stereotype.Service;
import com.setd.backendobservatorio.domain.repository.UserRepository;

@Service
public class ExistsByNomeUseCase {
    private final UserRepository userRepository;
    public ExistsByNomeUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public boolean existsByNome(String nome){
        return userRepository.existsByNome(nome);
    }
}
