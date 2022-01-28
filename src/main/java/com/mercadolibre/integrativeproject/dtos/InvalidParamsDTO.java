package com.mercadolibre.integrativeproject.dtos;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidParamsDTO {
    private Integer statusCode;
    private String message;
    Map<?, ?> arguments;
}
