package com.fag.lucasmartins.arquitetura_software.model;

public class Produto {
    private String nome;
    private double preco;
    private double precoFinal;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = preco;
    }

    // Regra de negócio: Validação de Produto Premium
    public void validarProdutoPremium() {
        if (this.nome != null && this.nome.toLowerCase().contains("premium")) {
            if (this.preco < 100.0) {
                throw new IllegalArgumentException("Erro: Produtos Premium não podem custar menos de R$ 100,00.");
            }
        }
    }

    // Regra de negócio: Cálculo de desconto por atacado
    public void calcularPrecoFinal() {
        if (this.estoque >= 50) {
            this.precoFinal = this.preco - (this.preco * 0.10); // 10% de desconto
        } else {
            this.precoFinal = this.preco;
        }
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public double getPrecoFinal() { return precoFinal; }
    public int getEstoque() { return estoque; }
}