package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Representative;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/** DTO de Registro de compra
 *
 * @author Lorraine Mendes
 * */

public class PurchaseRecordDTO {

    private Long id;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private Batch batch;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private Representative representative;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private BigInteger price;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private Long quantity;
}
