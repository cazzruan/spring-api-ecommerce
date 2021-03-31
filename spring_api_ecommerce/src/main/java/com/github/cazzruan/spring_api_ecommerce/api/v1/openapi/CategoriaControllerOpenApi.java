package com.github.cazzruan.spring_api_ecommerce.api.v1.openapi;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.CategoriaModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.CategoriaModelResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaControllerOpenApi {

    ResponseEntity<List<CategoriaModelResponse>> listar();
    ResponseEntity<CategoriaModelResponse> buscar(Long categoriaId);
    ResponseEntity<CategoriaModelResponse> salvar(CategoriaModelRequest categoriaModelRequest);
    ResponseEntity<CategoriaModelResponse> atualizar(Long categoriaId, CategoriaModelRequest categoriaModelRequest);
    ResponseEntity<Void> remover(Long categoriaId);

}
