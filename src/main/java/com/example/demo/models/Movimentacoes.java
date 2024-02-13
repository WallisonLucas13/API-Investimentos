package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
public class Movimentacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String ativo;

    @Column
    @NotNull
    private String categoria;

    @Column
    @NotNull
    private String segmento;

    @Column
    @NotNull
    private String operacao;

    @Column
    @NotNull
    private Double valorUnit;

    @Column
    @NotNull
    private int quant;

    @Column
    @NotNull
    private Double valorTotal;

    @Column
    @NotNull
    private String data;

    @Column
    @NotNull
    private String hora;

    @Column
    @NotNull
    private String corretora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCorretora() {
        return corretora;
    }

    public void setCorretora(String corretora) {
        this.corretora = corretora;
    }

    public static class MovimentacoesBuilder{

        private Movimentacoes movimentacoes = new Movimentacoes();

        //Builder - Retorna uma nova Instância do Builder
        public static MovimentacoesBuilder builder(){
            return new MovimentacoesBuilder();
        }

        public MovimentacoesBuilder ativo(String ativo){
            movimentacoes.ativo = ativo;
            return this;
        }

        public MovimentacoesBuilder categoria(String categoria){
            movimentacoes.categoria = categoria;
            return this;
        }

        public MovimentacoesBuilder segmento(String segmento){
            movimentacoes.segmento = segmento;
            return this;
        }

        public MovimentacoesBuilder operacao(String operacao){
            movimentacoes.operacao = operacao;
            return this;
        }

        public MovimentacoesBuilder valorUnit(Double valorUnit){
            movimentacoes.valorUnit = valorUnit;
            return this;
        }

        public MovimentacoesBuilder quant(int quant){
            movimentacoes.quant = quant;
            return this;
        }

        public MovimentacoesBuilder corretora(String corretora){
            movimentacoes.corretora = corretora.toUpperCase();
            return this;
        }

        private void valorTotal(){
            movimentacoes.valorTotal = movimentacoes.valorUnit * movimentacoes.quant;
        }
        private void data(){
            movimentacoes.data = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        }

        private void hora(){
            movimentacoes.hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
        }

        public Movimentacoes build(){
            valorTotal();
            data();
            hora();
            return movimentacoes;
        }
    }
}
