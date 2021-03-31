package com.github.cazzruan.spring_api_ecommerce.domain.service;

import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoInvalidoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoNaoEncontradoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.ValorDuplicadoException;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Cliente;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public CadastroClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarClienteExistente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", clienteId));

        return cliente;
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        verificarCpfDuplicado(cliente);
        verificarEmailDuplicado(cliente);
        verificarTelefoneDuplicado(cliente);
        cliente = clienteRepository.save(cliente);

        return cliente;
    }

    @Transactional
    public void removerCliente(Long clienteId) {
        buscarClienteExistente(clienteId);
        clienteRepository.deleteById(clienteId);
    }

    public void verificarClienteValido(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecursoInvalidoException("Cliente", clienteId));
    }

    public void verificarCpfDuplicado(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            throw new ValorDuplicadoException("Cliente", "cpf", clienteExistente.get().getCpf());
        }
    }

    public void verificarEmailDuplicado(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            throw new ValorDuplicadoException("Cliente", "e-mail", clienteExistente.get().getEmail());
        }
    }

    public void verificarTelefoneDuplicado(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByTelefone(cliente.getTelefone());
        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            throw new ValorDuplicadoException("Cliente", "telefone", clienteExistente.get().getTelefone());
        }
    }

}
