package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.ProdutoControllerOpenApi;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ProdutoModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper.ProdutoModelMapper;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ProdutoModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Produto;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.ProdutoRepository;
import com.github.cazzruan.spring_api_ecommerce.domain.service.CadastroProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController implements ProdutoControllerOpenApi {

    private ProdutoRepository produtoRepository;
    private CadastroProdutoService cadastroProdutoService;
    private ProdutoModelMapper produtoModelMapper;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, CadastroProdutoService cadastroProdutoService, ProdutoModelMapper produtoModelMapper) {
        this.produtoRepository = produtoRepository;
        this.cadastroProdutoService = cadastroProdutoService;
        this.produtoModelMapper = produtoModelMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoModelResponse> produtosModelResponse = produtoModelMapper.toCollectionModel(produtos);

        return new ResponseEntity<>(produtosModelResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoModelResponse> buscar(@PathVariable Long produtoId) {
        Produto produto = cadastroProdutoService.buscarProdutoExistente(produtoId);
        ProdutoModelResponse produtosModelResponse = produtoModelMapper.toModel(produto);

        return  new ResponseEntity<>(produtosModelResponse, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoModelResponse> salvar(@RequestBody @Valid ProdutoModelRequest produtoModelRequest) {
        Produto produto = produtoModelMapper.toEntity(produtoModelRequest);
        produto = cadastroProdutoService.salvarProduto(produto);
        ProdutoModelResponse produtosModelResponse = produtoModelMapper.toModel(produto);

        return new ResponseEntity<>(produtosModelResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoModelResponse> atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoModelRequest produtoModelRequest) {
        Produto produtoAtual = cadastroProdutoService.buscarProdutoExistente(produtoId);
        produtoModelMapper.copyToEntity(produtoModelRequest, produtoAtual);
        produtoAtual = cadastroProdutoService.salvarProduto(produtoAtual);
        ProdutoModelResponse produtosModelResponse = produtoModelMapper.toModel(produtoAtual);

        return new ResponseEntity<>(produtosModelResponse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> remover(@PathVariable Long produtoId) {
        cadastroProdutoService.removerProduto(produtoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
