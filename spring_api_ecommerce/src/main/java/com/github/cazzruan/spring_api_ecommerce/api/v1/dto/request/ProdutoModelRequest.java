package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModelRequest {

    @NotBlank(message = "{nome.not.blank}")
    private String nome;

    @NotBlank(message = "{descricao.not.blank}")
    private String descricao;

    @NotNull(message = "{preco.not.null}")
    private BigDecimal preco;

    @Valid
    @NotNull(message = "{categoria.not.null}")
    private CategoriaIdRequest categoria;

}
