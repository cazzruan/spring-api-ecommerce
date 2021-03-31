package com.github.cazzruan.spring_api_ecommerce.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private Integer quantidade;

    public void calcularSubTotal(){
        this.setSubTotal(precoUnitario.multiply(BigDecimal.valueOf(quantidade)));
    }

}
