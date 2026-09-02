package com.rental.eventos.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.eventos.models.Movimentacao;
import com.rental.eventos.services.MovimentacaoService;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoControllers {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoControllers(
            MovimentacaoService movimentacaoService) {

        this.movimentacaoService =
                movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarMovimentacao(
            @RequestBody MovimentacaoRequest dados) {

        try {

            Movimentacao movimentacao =
                    movimentacaoService.registrarMovimentacao(

                            dados.getUsuarioId(),

                            dados.getEquipamentoId(),

                            dados.getTipoMovimentacao(),

                            dados.getQuantidade()
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            criarResposta(movimentacao)
                    );

        } catch (NoSuchElementException erro) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Map.of(
                                    "erro",
                                    erro.getMessage()
                            )
                    );

        } catch (IllegalArgumentException erro) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "erro",
                                    erro.getMessage()
                            )
                    );
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>>
            listarMovimentacoes() {

        List<Map<String, Object>> resposta =
                movimentacaoService
                        .listarMovimentacoes()
                        .stream()
                        .map(this::criarResposta)
                        .toList();

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMovimentacaoPorId(
            @PathVariable Integer id) {

        Optional<Movimentacao> movimentacao =
                movimentacaoService
                        .buscarMovimentacaoPorId(id);

        if (movimentacao.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                criarResposta(
                        movimentacao.get()
                )
        );
    }

    private Map<String, Object> criarResposta(
            Movimentacao movimentacao) {

        Map<String, Object> resposta =
                new LinkedHashMap<>();

        resposta.put(
                "id",
                movimentacao.getId()
        );

        resposta.put(
                "usuarioId",
                movimentacao
                        .getUsuarioResponsavel()
                        .getId()
        );

        resposta.put(
                "usuarioNome",
                movimentacao
                        .getUsuarioResponsavel()
                        .getNome()
        );

        resposta.put(
                "equipamentoId",
                movimentacao
                        .getEquipamentoEnvolvido()
                        .getId()
        );

        resposta.put(
                "equipamentoNome",
                movimentacao
                        .getEquipamentoEnvolvido()
                        .getNome()
        );

        resposta.put(
                "dataMovimentacao",
                movimentacao.getDataMovimentacao()
        );

        resposta.put(
                "tipoMovimentacao",
                movimentacao.getTipoMovimentacao()
        );

        resposta.put(
                "quantidade",
                movimentacao.getQuantidade()
        );

        resposta.put(
                "estoqueAtual",
                movimentacao
                        .getEquipamentoEnvolvido()
                        .getQuantidade_estoque()
        );

        return resposta;
    }

    public static class MovimentacaoRequest {

        private Integer usuarioId;

        private Integer equipamentoId;

        private String tipoMovimentacao;

        private Integer quantidade;

        public MovimentacaoRequest() {

        }

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(
                Integer usuarioId) {

            this.usuarioId = usuarioId;
        }

        public Integer getEquipamentoId() {
            return equipamentoId;
        }

        public void setEquipamentoId(
                Integer equipamentoId) {

            this.equipamentoId =
                    equipamentoId;
        }

        public String getTipoMovimentacao() {
            return tipoMovimentacao;
        }

        public void setTipoMovimentacao(
                String tipoMovimentacao) {

            this.tipoMovimentacao =
                    tipoMovimentacao;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(
                Integer quantidade) {

            this.quantidade = quantidade;
        }
    }
}