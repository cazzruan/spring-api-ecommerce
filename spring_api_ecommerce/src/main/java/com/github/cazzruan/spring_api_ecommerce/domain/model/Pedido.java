package com.github.cazzruan.spring_api_ecommerce.domain.model;

import com.github.cazzruan.spring_api_ecommerce.domain.enums.StatusPedido;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.StatusPedidoAlterationException;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.AGUARDANDO;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public BigDecimal calcularValorTotal() {
        double sum = 0.0;
        for (ItemPedido item : itens) {
            sum += item.getSubTotal().doubleValue();
        }
        return BigDecimal.valueOf(sum);
    }

    public void confirmar() {
        if(!podeSerConfirmado()){
            throw new StatusPedidoAlterationException(getId(), getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao());
        }
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(LocalDateTime.now());
    }

    public void entregar() {
        if(!podeSerEntregue()){
            throw new StatusPedidoAlterationException(getId(), getStatus().getDescricao(), StatusPedido.ENTREGUE.getDescricao());
        }
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(LocalDateTime.now());
    }

    public void cancelar() {
        if(!podeSerCancelado()){
            throw new StatusPedidoAlterationException(getId(), getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao());
        }
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(LocalDateTime.now());
    }

    public boolean podeSerConfirmado() {
        return getStatus().equals(StatusPedido.AGUARDANDO);
    }

    public boolean podeSerEntregue() {
        return getStatus().equals(StatusPedido.CONFIRMADO);
    }

    public boolean podeSerCancelado() {
        return getStatus().equals(StatusPedido.AGUARDANDO);
    }

}
