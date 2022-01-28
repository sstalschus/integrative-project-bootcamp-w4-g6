package com.mercadolibre.integrativeproject.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageDTO {
    private Integer statusCode;
    private String message;
}
