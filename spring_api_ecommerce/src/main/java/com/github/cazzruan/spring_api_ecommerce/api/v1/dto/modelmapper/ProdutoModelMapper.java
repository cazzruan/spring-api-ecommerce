package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.ProdutoModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.ProdutoModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toEntity (ProdutoModelRequest produtoModelRequest) {
        return modelMapper.map(produtoModelRequest, Produto.class);
    }

    public void copyToEntity(ProdutoModelRequest produtoModelRequest, Produto produto) {
        modelMapper.map(produtoModelRequest, produto);

    }
    public ProdutoModelResponse toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModelResponse.class);
    }

    public List<ProdutoModelResponse> toCollectionModel(List<Produto> produtos) {
        return produtos
                .stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }

}
