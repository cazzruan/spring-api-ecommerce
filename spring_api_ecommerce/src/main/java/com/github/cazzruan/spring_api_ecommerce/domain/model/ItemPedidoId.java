package com.github.cazzruan.spring_api_ecommerce.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemPedidoId implements Serializable {

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "produto_id")
    private Long produtoId;

}
