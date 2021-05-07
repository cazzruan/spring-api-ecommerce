package com.github.cazzruan.spring_api_ecommerce.domain.service;

import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoNaoEncontradoException;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Cliente;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Pedido;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Produto;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissaoPedidoService {

    private PedidoRepository pedidoRepository;
    private CadastroProdutoService cadastroProdutoService;
    private CadastroClienteService cadastroClienteService;

    @Autowired
    public EmissaoPedidoService(PedidoRepository pedidoRepository, CadastroProdutoService cadastroProdutoService, CadastroClienteService cadastroClienteService) {
        this.pedidoRepository = pedidoRepository;
        this.cadastroProdutoService = cadastroProdutoService;
        this.cadastroClienteService = cadastroClienteService;
    }

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
       cadastroClienteService.verificarClienteValido(pedido.getCliente().getId());
       Cliente cliente = cadastroClienteService.buscarClienteExistente(pedido.getCliente().getId());

       pedido.setCliente(cliente);

    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            cadastroProdutoService.verificarProdutoValido(item.getProduto().getId());
            Produto produto = cadastroProdutoService.buscarProdutoExistente(item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    public Pedido buscarPedidoExistente(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido", pedidoId));
    }

}