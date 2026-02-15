package com.setd.backendobservatorio.usecase;
import org.springframework.stereotype.Service;

import com.setd.backendobservatorio.domain.model.User;
import com.setd.backendobservatorio.domain.repository.UserRepository;
import com.setd.backendobservatorio.usecase.dto.CreateUserInput;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(CreateUserInput userInput) {
        User user = new User(
            userInput.getNome(),
            userInput.getEmail(),
            userInput.getSenha(),
            userInput.getRoleId()
        );
        userRepository.save(user);
        return user;
    }
}
