package com.rental.eventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rental.eventos.models.Equipamentos;
import com.rental.eventos.repositories.EquipamentosRepository;

@Service
public class EquipamentosService {

    private final EquipamentosRepository equipamentosRepository;

    public EquipamentosService(EquipamentosRepository equipamentosRepository) {
        this.equipamentosRepository = equipamentosRepository;
    }

    public Equipamentos cadastrarEquipamento(Equipamentos equipamento) {
        return equipamentosRepository.save(equipamento);
    }

    public List<Equipamentos> listarEquipamentos() {
        return equipamentosRepository.findAll();
    }

    public Optional<Equipamentos> buscarEquipamentoPorId(Integer id) {
        return equipamentosRepository.findById(id);
    }

    public Optional<Equipamentos> atualizarEquipamento(
            Integer id,
            Equipamentos dadosAtualizados) {

        Optional<Equipamentos> equipamentoExistente =
                equipamentosRepository.findById(id);

        if (equipamentoExistente.isEmpty()) {
            return Optional.empty();
        }

        Equipamentos equipamento = equipamentoExistente.get();

        equipamento.setNome(dadosAtualizados.getNome());
        equipamento.setDescricao(dadosAtualizados.getDescricao());
        equipamento.setEspecificacao(dadosAtualizados.getEspecificacao());
        equipamento.setValor_diaria(dadosAtualizados.getValor_diaria());
        equipamento.setQuantidade_estoque(dadosAtualizados.getQuantidade_estoque());
        equipamento.setEstoque_minimo(dadosAtualizados.getEstoque_minimo());
        equipamento.setMarca(dadosAtualizados.getMarca());
        equipamento.setCategoria(dadosAtualizados.getCategoria());
        equipamento.setModelo(dadosAtualizados.getModelo());
        equipamento.setPotencia(dadosAtualizados.getPotencia());
        equipamento.setMaterial(dadosAtualizados.getMaterial());
        equipamento.setPeso(dadosAtualizados.getPeso());
        equipamento.setDimensoes(dadosAtualizados.getDimensoes());
        equipamento.setCor(dadosAtualizados.getCor());

        return Optional.of(
                equipamentosRepository.save(equipamento)
        );
    }

    public boolean excluirEquipamento(Integer id) {

        if (!equipamentosRepository.existsById(id)) {
            return false;
        }

        equipamentosRepository.deleteById(id);

        return true;
    }

    public boolean estoqueBaixo(Integer id) {

        Optional<Equipamentos> equipamento =
                equipamentosRepository.findById(id);

        if (equipamento.isEmpty()) {
            return false;
        }

        Integer quantidadeEstoque =
                equipamento.get().getQuantidade_estoque();

        Integer estoqueMinimo =
                equipamento.get().getEstoque_minimo();

        if (quantidadeEstoque == null || estoqueMinimo == null) {
            return false;
        }

        return quantidadeEstoque <= estoqueMinimo;
    }
}