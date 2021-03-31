package com.github.cazzruan.spring_api_ecommerce.domain.service;

import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoInvalidoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoNaoEncontradoException;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Produto;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.CategoriaRepository;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    private ProdutoRepository produtoRepository;
    private CadastroCategoriaService cadastroCategoriaService;

    @Autowired
    public CadastroProdutoService(ProdutoRepository produtoRepository, CadastroCategoriaService cadastroCategoriaService) {
        this.produtoRepository = produtoRepository;
        this.cadastroCategoriaService = cadastroCategoriaService;
    }

    public Produto buscarProdutoExistente(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", produtoId));

        return produto;
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        cadastroCategoriaService.verificarCategoriaValida(produto.getCategoria().getId());
        produto = produtoRepository.save(produto);

        return produto;
    }

    @Transactional
    public void removerProduto(Long produtoId) {
        buscarProdutoExistente(produtoId);
        produtoRepository.deleteById(produtoId);

    }

    public void verificarProdutoValido(Long produtoId) {
        produtoRepository.findById(produtoId)
               .orElseThrow(() -> new RecursoInvalidoException("Produto", produtoId));
    }

}
