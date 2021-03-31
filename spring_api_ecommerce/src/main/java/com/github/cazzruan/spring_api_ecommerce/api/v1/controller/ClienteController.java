package com.github.cazzruan.spring_api_ecommerce.api.v1.controller;

import com.github.cazzruan.spring_api_ecommerce.api.v1.openapi.ClienteControllerOpenApi;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ClienteModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper.ClienteModelMapper;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ClienteModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Cliente;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.ClienteRepository;
import com.github.cazzruan.spring_api_ecommerce.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController implements ClienteControllerOpenApi {

    private ClienteRepository clienteRepository;
    private CadastroClienteService cadastroClienteService;
    private ClienteModelMapper clienteModelMapper;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, CadastroClienteService cadastroClienteService, ClienteModelMapper clienteModelMapper) {
        this.clienteRepository = clienteRepository;
        this.cadastroClienteService = cadastroClienteService;
        this.clienteModelMapper = clienteModelMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteModelResponse>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteModelResponse> clientesModelResponse = clienteModelMapper.toCollectionModel(clientes);

        return new ResponseEntity<>(clientesModelResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModelResponse> buscar(@PathVariable Long clienteId) {
        Cliente cliente = cadastroClienteService.buscarClienteExistente(clienteId);
        ClienteModelResponse clienteModelResponse = clienteModelMapper.toModel(cliente);

        return new ResponseEntity<>(clienteModelResponse, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteModelResponse> salvar(@RequestBody @Valid ClienteModelRequest clienteModelRequest) {
        Cliente cliente = clienteModelMapper.toEntity(clienteModelRequest);
        cliente = cadastroClienteService.salvarCliente(cliente);
        ClienteModelResponse clienteModelResponse = clienteModelMapper.toModel(cliente);

        return new ResponseEntity<>(clienteModelResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModelResponse> atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteModelRequest clienteModelRequest) {
        Cliente clienteAtual = cadastroClienteService.buscarClienteExistente(clienteId);
        clienteModelMapper.copyToEntity(clienteModelRequest, clienteAtual);
        clienteAtual = cadastroClienteService.salvarCliente(clienteAtual);
        ClienteModelResponse clienteModelResponse = clienteModelMapper.toModel(clienteAtual);

        return new ResponseEntity<>(clienteModelResponse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        cadastroClienteService.removerCliente(clienteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
