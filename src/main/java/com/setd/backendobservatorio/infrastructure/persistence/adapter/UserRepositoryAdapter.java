package com.setd.backendobservatorio.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.setd.backendobservatorio.domain.model.User;
import com.setd.backendobservatorio.domain.repository.UserRepository;
import com.setd.backendobservatorio.infrastructure.persistence.entity.UserEntity;
import com.setd.backendobservatorio.infrastructure.persistence.repository.JpaUserRepository;

@Component
public class UserRepositoryAdapter implements  UserRepository {
    private final JpaUserRepository jpaUserRepository;
    
    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setNome(user.getNome());
        userEntity.setSenha(user.getSenha());
        userEntity.setEmail(user.getEmail());
        userEntity.setRoleId(user.getRoleId());
        jpaUserRepository.save(userEntity);
        return user;
    }
}
