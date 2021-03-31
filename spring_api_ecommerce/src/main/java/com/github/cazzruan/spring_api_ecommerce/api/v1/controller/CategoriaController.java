package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.CategoriaControllerOpenApi;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.CategoriaModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper.CategoriaModelMapper;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.CategoriaModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Categoria;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.CategoriaRepository;
import com.github.cazzruan.spring_api_ecommerce.domain.service.CadastroCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController implements CategoriaControllerOpenApi {

    private CategoriaRepository categoriaRepository;
    private CadastroCategoriaService cadastroCategoriaService;
    private CategoriaModelMapper categoriaModelMapper;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository, CadastroCategoriaService cadastroCategoriaService, CategoriaModelMapper categoriaModelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.cadastroCategoriaService = cadastroCategoriaService;
        this.categoriaModelMapper = categoriaModelMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoriaModelResponse>> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaModelResponse> categoriasModelResponse = categoriaModelMapper.toCollectionModel(categorias);

        return new ResponseEntity<>(categoriasModelResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaModelResponse> buscar(@PathVariable Long categoriaId) {
        Categoria categoria = cadastroCategoriaService.buscarCategoriaExistente(categoriaId);
        CategoriaModelResponse categoriaModelResponse = categoriaModelMapper.toModel(categoria);

        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoriaModelResponse> salvar(@RequestBody @Valid CategoriaModelRequest categoriaModelRequest) {
        Categoria novaCategoria = categoriaModelMapper.toEntity(categoriaModelRequest);
        novaCategoria =  cadastroCategoriaService.salvarCategoria(novaCategoria);
        CategoriaModelResponse categoriaModelResponse = categoriaModelMapper.toModel(novaCategoria);

        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaModelResponse> atualizar(@PathVariable Long categoriaId, @RequestBody @Valid CategoriaModelRequest categoriaModelRequest) {
        Categoria categoriaAtual = cadastroCategoriaService.buscarCategoriaExistente(categoriaId);
        categoriaModelMapper.copyToEntity(categoriaModelRequest, categoriaAtual);
        categoriaAtual = cadastroCategoriaService.salvarCategoria(categoriaAtual);
        CategoriaModelResponse categoriaModelResponse = categoriaModelMapper.toModel(categoriaAtual);

        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> remover(@PathVariable Long categoriaId) {
        cadastroCategoriaService.buscarCategoriaExistente(categoriaId);
        cadastroCategoriaService.removerCategoria(categoriaId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
