package com.rental.eventos.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rental.eventos.models.Usuarios;
import com.rental.eventos.repositories.UsuariosRepository;

@Service
public class UsuariosServices {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServices(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Usuarios cadastrarUsuario(Usuarios usuario) {

        usuario.setTipo_usuario("cliente");

        return usuariosRepository.save(usuario);
    }

    public Optional<Usuarios> verificarLogin(String email, String senha) {

        return usuariosRepository.findByEmailAndSenha(email, senha);
    }

    public long countUsuarios() {
        return usuariosRepository.count();
    }
}