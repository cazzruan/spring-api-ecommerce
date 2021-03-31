package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.response;

import com.github.cazzruan.spring_api_ecommerce.domain.enums.SexoCliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteModelResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private SexoCliente sexo;

}
