package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoriaIdRequest {

    @NotNull(message = "{categoria.not.null}")
    private Long id;

}
