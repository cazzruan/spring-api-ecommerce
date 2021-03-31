package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.modelmapper;

import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request.CategoriaModelRequest;
import com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response.CategoriaModelResponse;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Categoria toEntity (CategoriaModelRequest categoriaModelRequest) {
        return modelMapper.map(categoriaModelRequest, Categoria.class);
    }

    public void copyToEntity(CategoriaModelRequest categoriaModelRequest, Categoria categoria) {
        modelMapper.map(categoriaModelRequest, categoria);

    }

    public CategoriaModelResponse toModel(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaModelResponse.class);
    }

    public List<CategoriaModelResponse> toCollectionModel(List<Categoria> categorias) {
        return categorias
                .stream()
                .map(categoria -> toModel(categoria))
                .collect(Collectors.toList());
    }

}
