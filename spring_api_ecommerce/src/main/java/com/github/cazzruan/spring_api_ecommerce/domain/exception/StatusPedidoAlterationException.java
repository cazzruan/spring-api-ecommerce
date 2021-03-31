package com.github.cazzruan.spring_api_ecommerce.domain.exception;

public class StatusPedidoAlterationException extends RuntimeException {

    public StatusPedidoAlterationException(Long id, String status, String novoStatus ) {
        String.format("Status do pedido %s não pode ser alterado de %s para %s", id, status, novoStatus);
    }

}
