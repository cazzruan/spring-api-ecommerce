package com.github.cazzruan.spring_api_ecommerce.domain.service;

import com.github.cazzruan.spring_api_ecommerce.domain.model.Pedido;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    private EmissaoPedidoService emissaoPedidoService;
    private PedidoRepository pedidoRepository;

    @Autowired
    public FluxoPedidoService(EmissaoPedidoService emissaoPedido, PedidoRepository pedidoRepository) {
        this.emissaoPedidoService = emissaoPedido;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarPedidoExistente(pedidoId);
        pedido.confirmar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarPedidoExistente(pedidoId);
        pedido.cancelar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarPedidoExistente(pedidoId);
        pedido.entregar();

        pedidoRepository.save(pedido);
    }

}