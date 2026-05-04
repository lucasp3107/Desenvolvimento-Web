package com.fag.lucasmartins.arquitetura_software.dto;

public class ProdutoResponseDTO {
    private String mensagem;
    private String nome;
    private int estoque;
    private double preco;
    private double precoFinal;

    public ProdutoResponseDTO(String mensagem, String nome, int estoque, double preco, double precoFinal) {
        this.mensagem = mensagem;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.precoFinal = precoFinal;
    }

    public String getMensagem() { return mensagem; }
    public String getNome() { return nome; }
    public int getEstoque() { return estoque; }
    public double getPreco() { return preco; }
    public double getPrecoFinal() { return precoFinal; }
}