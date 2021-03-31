package com.github.cazzruan.spring_api_ecommerce.domain.exception;

public class ValorDuplicadoException extends RuntimeException {

    public ValorDuplicadoException(String entidadeNome, String entidadeCampo, String valor) {
        super(String.format("Já existe um(a) %s cadastrado(a) com o(a) %s %s no sistema.", entidadeNome, entidadeCampo, valor));
    }

}
