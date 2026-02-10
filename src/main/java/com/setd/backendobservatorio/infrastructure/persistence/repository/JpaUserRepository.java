package com.setd.backendobservatorio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setd.backendobservatorio.domain.model.User;
import com.setd.backendobservatorio.infrastructure.persistence.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    User save(User user);
    boolean existsByNome(String nome);

    UserEntity findByNomeAndSenha(String nome, String senha);
}
