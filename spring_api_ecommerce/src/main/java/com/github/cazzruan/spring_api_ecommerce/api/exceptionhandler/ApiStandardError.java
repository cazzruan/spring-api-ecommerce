package com.github.cazzruan.spring_api_ecommerce.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiStandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String title;
    private String message;
    private String path;
    private List<FieldErrorDetails> fieldsErrorDetails;

}
