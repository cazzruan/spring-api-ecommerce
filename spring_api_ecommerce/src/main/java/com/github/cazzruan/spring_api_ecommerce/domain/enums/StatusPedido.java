package com.github.cazzruan.spring_api_ecommerce.domain.enums;

public enum StatusPedido {

    AGUARDANDO("Aguardando"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;


    StatusPedido(String descricao) {
        this.descricao = descricao;

    }

    public String getDescricao() {
        return this.descricao;
    }


}
