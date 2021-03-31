package com.github.cazzruan.spring_api_ecommerce.api.exceptionhandler;



import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoInvalidoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.ValorDuplicadoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoNaoEncontradoException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest swrequest = (ServletWebRequest) request;
        List<FieldErrorDetails> errors = getFieldsErrorDetails(ex);

        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Falha na validação",
                "Um ou mais campos estão inválidos", swrequest.getRequest().getRequestURI(), errors );

        return new ResponseEntity<Object>(error, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest swrequest = (ServletWebRequest) request;
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, status, request);
        }

        ApiStandardError error = getApiStandardError(HttpStatus.NOT_FOUND.value(), ex.getCause().getMessage(),
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null );

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpStatus status, WebRequest request) {
        ServletWebRequest swrequest = (ServletWebRequest) request;
        String proriedade =  ex.getPath().stream().map(reference -> reference.getFieldName()).collect(Collectors.joining(", "));
        List<String> valoresEsperados =  Arrays.stream(ex.getTargetType().getFields()).map(f -> f.getName()).collect(Collectors.toList());

        String message =  String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Valores esperados: %s.", proriedade, ex.getValue(), valoresEsperados);

        ApiStandardError error = getApiStandardError(status.value(), "Formato inválido",
                message, swrequest.getRequest().getRequestURI(), null );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    private  ResponseEntity<ApiStandardError> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.NOT_FOUND.value(), "Entidade não encontrada",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoInvalidoException.class)
    private  ResponseEntity<ApiStandardError> handleRecursoInvalido(RecursoInvalidoException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Recurso inválido",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValorDuplicadoException.class)
    private ResponseEntity<ApiStandardError> handleValorDuplicado(ValorDuplicadoException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Valor já registrado",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(StatusAlunoChangeException.class)
//    private ResponseEntity<ApiStandardError> handleStatusAlunoChange(StatusAlunoChangeException ex, ServletWebRequest swrequest){
//        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Unable Action",
//                ex.getMessage(), swrequest.getRequest().getRequestURI(), null);
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

    private ApiStandardError getApiStandardError(Integer status, String error, String message, String path, List<FieldErrorDetails> fieldsErrorDetails){
        return ApiStandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .title(error)
                .message(message)
                .path(path)
                .fieldsErrorDetails(fieldsErrorDetails)
                .build();
    }

    private List<FieldErrorDetails> getFieldsErrorDetails(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> FieldErrorDetails.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .parameter(error.getRejectedValue())
                                .build())
                .collect(Collectors.toList());
    }

}
