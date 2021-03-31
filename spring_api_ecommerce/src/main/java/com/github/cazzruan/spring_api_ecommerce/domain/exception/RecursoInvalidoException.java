package com.github.cazzruan.spring_api_ecommerce.domain.exception;

public class RecursoInvalidoException extends RuntimeException {

    public RecursoInvalidoException(String recursoNome, Long recursoId) {
        super(String.format("Não existe um(a) %s com ID %s cadastrado(a) no sistema.", recursoNome, recursoId));
    }

}
