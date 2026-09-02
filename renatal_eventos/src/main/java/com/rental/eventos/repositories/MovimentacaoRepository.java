package com.rental.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.eventos.models.Movimentacao;


public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    
}
