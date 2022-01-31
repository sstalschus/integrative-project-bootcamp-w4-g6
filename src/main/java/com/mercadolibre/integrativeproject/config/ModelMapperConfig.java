package com.mercadolibre.integrativeproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Config de dependência para facilitar na conversão das entidades/dtos.
 *
 * @author Samuel Stalschus
 *
 * */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
