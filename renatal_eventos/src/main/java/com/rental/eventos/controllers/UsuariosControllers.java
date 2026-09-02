package com.rental.eventos.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rental.eventos.models.Usuarios;
import com.rental.eventos.services.UsuariosServices;

@Controller
public class UsuariosControllers {

    private final UsuariosServices usuariosServices;

    public UsuariosControllers(UsuariosServices usuariosServices) {
        this.usuariosServices = usuariosServices;
    }


    @PostMapping("/cadastro")
    public String cadastrarUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String cpf) {

        Usuarios usuario = new Usuarios();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setCpf(cpf);

        usuariosServices.cadastrarUsuario(usuario);

        return "redirect:/index.html?cadastro=sucesso";
    }


    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String email,
            @RequestParam String senha) {

        Optional<Usuarios> usuario =
                usuariosServices.verificarLogin(email, senha);

        if (usuario.isPresent()) {
            return "redirect:/index.html?login=sucesso";
        }

        return "redirect:/index.html?login=erro";
    }
}