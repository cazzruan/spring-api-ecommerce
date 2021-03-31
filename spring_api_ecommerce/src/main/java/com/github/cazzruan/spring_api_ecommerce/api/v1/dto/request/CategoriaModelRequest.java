package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoriaModelRequest {

    @NotBlank(message = "{nome.not.blank}")
    private String nome;

}
