package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.PedidoControllerOpenApi;
import com.github.cazzruan.spring_api_ecommerce.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    public PedidoController(EmissaoPedidoService emissaoPedidoService) {
        this.emissaoPedidoService = emissaoPedidoService;
    }

    public ResponseEntity<Pedido> findPedidoById
}
