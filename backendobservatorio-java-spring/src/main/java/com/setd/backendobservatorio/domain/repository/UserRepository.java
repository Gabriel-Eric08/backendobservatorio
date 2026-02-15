package com.setd.backendobservatorio.domain.repository;

import com.setd.backendobservatorio.domain.model.User;

public interface UserRepository {
    User save(User user);
    boolean existsByNome(String nome);
    User findByNomeAndSenha(String nome, String senha);
}
