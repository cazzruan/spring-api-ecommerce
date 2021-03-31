package com.github.cazzruan.spring_api_ecommerce.api.v1.dto.request;

import com.github.cazzruan.spring_api_ecommerce.domain.enums.SexoCliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteModelRequest {

    @NotBlank(message = "{nome.not.blank}")
    private String nome;

    @NotBlank(message = "{cpf.not.blank}")
    @Size(min = 11, max = 11, message = "{cpf.not.size.valid}")
    private String cpf;

    @NotBlank(message = "{telefone.not.blank}")
    @Size(min = 11, max = 11, message = "{telefone.not.size.valid}")
    private String telefone;

    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotNull(message = "{data_nascimento.not.blank}")
    @DateTimeFormat
    private LocalDate dataNascimento;

    @NotNull(message = "{sexo.not.null}")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

}
