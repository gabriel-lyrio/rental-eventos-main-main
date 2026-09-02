package com.rental.eventos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.eventos.models.Equipamentos;
import com.rental.eventos.models.Movimentacao;
import com.rental.eventos.models.Usuarios;
import com.rental.eventos.repositories.EquipamentosRepository;
import com.rental.eventos.repositories.MovimentacaoRepository;
import com.rental.eventos.repositories.UsuariosRepository;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final EquipamentosRepository equipamentosRepository;
    private final UsuariosRepository usuariosRepository;

    public MovimentacaoService(
            MovimentacaoRepository movimentacaoRepository,
            EquipamentosRepository equipamentosRepository,
            UsuariosRepository usuariosRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.equipamentosRepository = equipamentosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public Movimentacao registrarMovimentacao(
            Integer usuarioId,
            Integer equipamentoId,
            String tipoMovimentacao,
            Integer quantidade) {

        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "O usuário responsável é obrigatório."
            );
        }

        if (equipamentoId == null) {
            throw new IllegalArgumentException(
                    "O equipamento é obrigatório."
            );
        }

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        if (tipoMovimentacao == null || tipoMovimentacao.isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo de movimentação é obrigatório."
            );
        }

        String tipo = tipoMovimentacao.trim().toLowerCase();

        if (tipo.equals("saída")) {
            tipo = "saida";
        }

        if (!tipo.equals("entrada") && !tipo.equals("saida")) {
            throw new IllegalArgumentException(
                    "O tipo de movimentação deve ser 'entrada' ou 'saida'."
            );
        }

        Usuarios usuario = usuariosRepository
                .findById(usuarioId)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "Usuário não encontrado."
                        )
                );

        Equipamentos equipamento = equipamentosRepository
                .findById(equipamentoId)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "Equipamento não encontrado."
                        )
                );

        Integer estoqueAtual =
                equipamento.getQuantidade_estoque();

        if (estoqueAtual == null) {
            estoqueAtual = 0;
        }

        if (tipo.equals("entrada")) {

            equipamento.setQuantidade_estoque(
                    estoqueAtual + quantidade
            );
        }

        if (tipo.equals("saida")) {

            if (estoqueAtual < quantidade) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente. Quantidade disponível: "
                        + estoqueAtual
                        + "."
                );
            }

            equipamento.setQuantidade_estoque(
                    estoqueAtual - quantidade
            );
        }

        equipamentosRepository.save(equipamento);

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setUsuarioResponsavel(usuario);

        movimentacao.setEquipamentoEnvolvido(
                equipamento
        );

        movimentacao.setDataMovimentacao(
                LocalDateTime.now()
        );

        movimentacao.setTipoMovimentacao(tipo);

        movimentacao.setQuantidade(quantidade);

        return movimentacaoRepository.save(
                movimentacao
        );
    }

    public List<Movimentacao> listarMovimentacoes() {

        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarMovimentacaoPorId(
            Integer id) {

        return movimentacaoRepository.findById(id);
    }
}