package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ClienteModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ClienteModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toEntity (ClienteModelRequest clienteModelRequest) {
        return modelMapper.map(clienteModelRequest, Cliente.class);
    }

    public void copyToEntity(ClienteModelRequest clienteModelRequest, Cliente cliente) {
        modelMapper.map(clienteModelRequest, cliente);

    }
    public ClienteModelResponse toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModelResponse.class);
    }

    public List<ClienteModelResponse> toCollectionModel(List<Cliente> clientes) {
        return clientes
                .stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }

}
