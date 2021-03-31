package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response;

import com.github.cazzruan.spring_api_ecommerce.domain.model.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoModelResponse {

    private Long id;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataUltimaAtualizacao = LocalDateTime.now();;
    private String nome;
    private String descricao;
    private Categoria categoria;

}
