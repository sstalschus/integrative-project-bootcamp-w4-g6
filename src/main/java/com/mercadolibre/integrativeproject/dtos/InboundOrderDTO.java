package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.InboundOrder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InboundOrderDTO {

    @NotEmpty
    @NotNull
    private Long orderNumber;
    @NotEmpty
    @NotNull
    private LocalDate  orderDate;

    @NotNull
    private SectionInboundOrderDTO sectionInboundOrderDTO;

    private List<BatchDTO> batch = new ArrayList<>();

    public SectionInboundOrderDTO getSectorDTO() {
        return sectionInboundOrderDTO;
    }

    public void setSectorDTO(SectionInboundOrderDTO sectionInboundOrderDTO) {
        this.sectionInboundOrderDTO = sectionInboundOrderDTO;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<BatchDTO> getBatch() {
        return batch;
    }

    public void setBatch(List<BatchDTO> batch) {
        this.batch = batch;
    }

    public InboundOrder convert() {
        List<Batch> batches = batch.stream().map(BatchDTO::coverte).collect(Collectors.toList());
        return InboundOrder.builder()
                .orderDate(this.orderDate)
                .orderNumber(this.orderNumber)
                .sectionCode(this.sectionInboundOrderDTO.getSectionCode())
                .warehouseCode(this.sectionInboundOrderDTO.getWarehouseCode())
                .batches(batches)
                .build();
    }
}
