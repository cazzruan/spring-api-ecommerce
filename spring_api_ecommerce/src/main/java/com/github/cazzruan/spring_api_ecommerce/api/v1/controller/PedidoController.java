package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.PedidoControllerOpenApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

}
