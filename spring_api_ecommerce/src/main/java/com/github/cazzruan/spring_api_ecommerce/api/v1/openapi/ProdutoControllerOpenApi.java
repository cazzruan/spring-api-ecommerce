package com.github.cazzruan.spring_api_ecommerce.api.v1.openapi;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ProdutoModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ProdutoModelResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProdutoControllerOpenApi {
    ResponseEntity<List<ProdutoModelResponse>> listar();
    ResponseEntity<ProdutoModelResponse> buscar(Long produtoId);
    ResponseEntity<ProdutoModelResponse> salvar(ProdutoModelRequest produtoModelRequest);
    ResponseEntity<ProdutoModelResponse> atualizar(Long produtoId, ProdutoModelRequest produtoModelRequest);
    ResponseEntity<Void> remover(Long produtoId);
}
