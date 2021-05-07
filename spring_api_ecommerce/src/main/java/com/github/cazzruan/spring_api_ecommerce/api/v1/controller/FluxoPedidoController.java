package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.FluxoPedidoControllerOpenApi;
import com.github.cazzruan.spring_api_ecommerce.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/pedidos/{pedidoId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {

    private FluxoPedidoService fluxoPedidoService;

    @Autowired
    public FluxoPedidoController(FluxoPedidoService fluxoPedido) {
        this.fluxoPedidoService = fluxoPedido;
    }

    @Override
    @PutMapping("/confirmacao")
    public ResponseEntity<Void> confirmar(@PathVariable Long pedidoId) {
        fluxoPedidoService.confirmar(pedidoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/cancelamento")
    public ResponseEntity<Void> cancelar(@PathVariable Long pedidoId) {
        fluxoPedidoService.cancelar(pedidoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/entrega")
    public ResponseEntity<Void> entregar(@PathVariable Long pedidoId) {
        fluxoPedidoService.entregar(pedidoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}