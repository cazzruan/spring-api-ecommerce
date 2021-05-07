package com.github.cazzruan.spring_api_ecommerce.api.v1.openapi;

import org.springframework.http.ResponseEntity;

public interface FluxoPedidoControllerOpenApi {

    ResponseEntity<Void> confirmar(Long pedidoId);
    ResponseEntity<Void> cancelar(Long pedidoId);
    ResponseEntity<Void> entregar(Long pedidoId);

}
