package com.github.cazzruan.spring_api_ecommerce.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldErrorDetails {

    private String message;
    private String field;
    private Object parameter;

}
