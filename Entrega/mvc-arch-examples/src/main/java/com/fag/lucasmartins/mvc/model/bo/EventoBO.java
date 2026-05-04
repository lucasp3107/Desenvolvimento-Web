package com.fag.lucasmartins.mvc.model.bo;

public class EventoBO {

    private Long id;

    private String nome;

    private Integer capacidadeMaxima;

    private Integer ingressosVendidos;

    public EventoBO() {
    }

    public EventoBO(Long id, String nome, Integer capacidadeMaxima) {
        this.id = id;
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.ingressosVendidos = 0;
    }

    public EventoBO(Long id, String nome, Integer capacidadeMaxima, Integer ingressosVendidos) {
        this.id = id;
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.ingressosVendidos = ingressosVendidos;
    }

    public void registrarVenda() {
        if (this.ingressosVendidos >= this.capacidadeMaxima) {
            throw new RuntimeException("O evento já se encontra esgotado!");
        }
        this.ingressosVendidos++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Integer getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(Integer ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }


}