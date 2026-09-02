package com.rental.eventos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

@Entity
@Table(name="movimentacao")   
public class Movimentacao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_responsavel", nullable = false)
    private Usuarios usuarioResponsavel;

    @ManyToOne
    @JoinColumn(name = "equipamento_envolvido", nullable = false)
    private Equipamentos equipamentoEnvolvido;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(name = "tipo_movimentacao", nullable = false, length = 30)
    private String tipoMovimentacao;

    @Column(nullable = false)
    private Integer quantidade;

    public Movimentacao() {
    }

    public Movimentacao(Integer id, Usuarios usuarioResponsavel, Equipamentos equipamentoEnvolvido,
            LocalDateTime dataMovimentacao, String tipoMovimentacao, Integer quantidade) {
        this.id = id;
        this.usuarioResponsavel = usuarioResponsavel;
        this.equipamentoEnvolvido = equipamentoEnvolvido;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuarios getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuarios usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Equipamentos getEquipamentoEnvolvido() {
        return equipamentoEnvolvido;
    }

    public void setEquipamentoEnvolvido(Equipamentos equipamentoEnvolvido) {
        this.equipamentoEnvolvido = equipamentoEnvolvido;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }   

    

}
