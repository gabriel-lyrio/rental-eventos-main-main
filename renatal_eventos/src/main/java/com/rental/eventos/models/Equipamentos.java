package com.rental.eventos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="equipamentos")   
public class Equipamentos {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;     
    
    @Column(name="especificacao")
    private String especificacao;

    @Column(name="valor_diaria")
    private Double valor_diaria;

    @Column(name="quantidade_estoque")
    private Integer quantidade_estoque;

    @Column(name="estoque_minimo")
    private Integer estoque_minimo;

    @Column(name="marca")
    private String marca;

    @Column(name="categoria")
    private String categoria;

    @Column(name="modelo")
    private String modelo;

    @Column(name="potencia")
    private String potencia;

    @Column(name="material")
    private String material;

    @Column(name="peso")
    private String peso;

    @Column(name="dimensoes")
    private String dimensoes;

    @Column(name="cor")
    private String cor; 

    public Equipamentos(){

    }

    public Equipamentos(Integer id, String nome, String descricao, String especificacao, Double valor_diaria,
            Integer quantidade_estoque, Integer estoque_minimo, String marca, String categoria, String modelo,
            String potencia, String material, String peso, String dimensoes, String cor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.especificacao = especificacao;
        this.valor_diaria = valor_diaria;
        this.quantidade_estoque = quantidade_estoque;
        this.estoque_minimo = estoque_minimo;
        this.marca = marca;
        this.categoria = categoria;
        this.modelo = modelo;
        this.potencia = potencia;
        this.material = material;
        this.peso = peso;
        this.dimensoes = dimensoes;
        this.cor = cor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public Double getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(Double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public Integer getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(Integer quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public Integer getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(Integer estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}



 