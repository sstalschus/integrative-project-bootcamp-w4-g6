package com.mercadolibre.integrativeproject.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InboundOrderDTO {

    @NotEmpty
    @NotNull
    private Long orderNumber;
    @NotEmpty
    @NotNull
    private LocalDate  orderDate;

    private List<BatchDTO> batch = new ArrayList<>();

    public InboundOrderDTO() {
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
}
