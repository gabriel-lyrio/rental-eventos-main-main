package com.rental.eventos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.eventos.models.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByEmailAndSenha(String email, String senha);

}