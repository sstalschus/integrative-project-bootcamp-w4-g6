package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Discount;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.enums.DiscountRange;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** Entidade DTO de desconto, para o requisito 6 de Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountDTO {

    private Long id;

    private AdvertsDTO advert;

    private BigDecimal previousPrice;

    private BigDecimal currentPrice;

    private String discountRange;

    private Timestamp createdAt;

    public static DiscountDTO convert(Discount discount) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(discount, DiscountDTO.class);
    }

    public static Discount convert(DiscountDTO discountDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(discountDTO, Discount.class);
    }
}
