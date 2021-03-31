package com.github.cazzruan.spring_api_ecommerce.api.v1.openapi;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ClienteModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ClienteModelResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteControllerOpenApi {

    ResponseEntity<List<ClienteModelResponse>> listar();
    ResponseEntity<ClienteModelResponse> buscar(Long clienteId);
    ResponseEntity<ClienteModelResponse> salvar(ClienteModelRequest clienteModelRequest);
    ResponseEntity<ClienteModelResponse> atualizar(Long clienteId, ClienteModelRequest clienteModelRequest);
    ResponseEntity<Void> remover(Long clienteId);

}
