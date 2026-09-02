package com.rental.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.eventos.models.Equipamentos;
import com.rental.eventos.services.EquipamentosService;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentosControllers {

    private final EquipamentosService equipamentosService;

    public EquipamentosControllers(
            EquipamentosService equipamentosService) {

        this.equipamentosService = equipamentosService;
    }

    @PostMapping
    public ResponseEntity<Equipamentos> cadastrarEquipamento(
            @RequestBody Equipamentos equipamento) {

        Equipamentos equipamentoSalvo =
                equipamentosService.cadastrarEquipamento(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(equipamentoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Equipamentos>> listarEquipamentos() {

        return ResponseEntity.ok(
                equipamentosService.listarEquipamentos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamentos> buscarEquipamentoPorId(
            @PathVariable Integer id) {

        Optional<Equipamentos> equipamento =
                equipamentosService.buscarEquipamentoPorId(id);

        if (equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(equipamento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamentos> atualizarEquipamento(
            @PathVariable Integer id,
            @RequestBody Equipamentos equipamento) {

        Optional<Equipamentos> equipamentoAtualizado =
                equipamentosService.atualizarEquipamento(
                        id,
                        equipamento
                );

        if (equipamentoAtualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                equipamentoAtualizado.get()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEquipamento(
            @PathVariable Integer id) {

        boolean excluido =
                equipamentosService.excluirEquipamento(id);

        if (!excluido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/estoque-baixo")
    public ResponseEntity<Boolean> verificarEstoqueBaixo(
            @PathVariable Integer id) {

        Optional<Equipamentos> equipamento =
                equipamentosService.buscarEquipamentoPorId(id);

        if (equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                equipamentosService.estoqueBaixo(id)
        );
    }
}