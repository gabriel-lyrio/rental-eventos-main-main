package com.rental.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.eventos.models.Equipamentos;

public interface EquipamentosRepository extends JpaRepository<Equipamentos, Integer> {
    
}
