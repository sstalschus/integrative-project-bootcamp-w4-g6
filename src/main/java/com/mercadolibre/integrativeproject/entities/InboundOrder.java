package com.mercadolibre.integrativeproject.entities;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public class InboundOrder {

    @NotEmpty
    @NotNull
    private Long orderNumber;
    @NotEmpty
    @NotNull
    private LocalDate orderDate;

    private Long sectionCode;

    private Long warehouseCode;

    private Responsible responsible;

    private List<Batch> batches = new ArrayList<>();

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

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public Long getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Long sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Long getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Long warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }
}
