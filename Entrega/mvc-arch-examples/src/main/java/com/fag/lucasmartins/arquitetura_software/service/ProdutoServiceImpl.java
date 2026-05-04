package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.model.Produto;
import com.fag.lucasmartins.arquitetura_software.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        // 1. Cria a entidade de domínio
        Produto produto = new Produto(dto.getNome(), dto.getPreco(), dto.getEstoque());

        // 2. Executa as regras de negócio
        produto.validarProdutoPremium();
        produto.calcularPrecoFinal();

        // 3. Salva no banco de dados via repositório
        produtoRepository.salvar(produto);

        // 4. Retorna os dados formatados
        return new ProdutoResponseDTO(
                "Produto cadastrado com sucesso!",
                produto.getNome(),
                produto.getEstoque(),
                produto.getPreco(),
                produto.getPrecoFinal()
        );
    }
}