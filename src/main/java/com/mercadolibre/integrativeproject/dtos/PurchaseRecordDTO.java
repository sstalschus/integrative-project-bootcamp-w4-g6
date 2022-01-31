package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/** DTO de Registro de compra
 *
 * @author Lorraine Mendes
 * */

public class PurchaseRecordDTO {

    private Long id;

    @Valid
    private BatchDTO batchDTO;

    @Valid
    private ResponsibleDTO responsibleDTO;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private BigInteger price;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private Long quantity;

    public static PurchaseRecord convert(PurchaseRecordDTO purchaseRecordDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(purchaseRecordDTO, PurchaseRecord.class);
    }

    public static PurchaseRecordDTO convert(PurchaseRecord purchaseRecord) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(purchaseRecord, PurchaseRecordDTO.class);
    }
}
