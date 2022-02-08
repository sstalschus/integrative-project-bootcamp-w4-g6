package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.InboundOrder;
import com.mercadolibre.integrativeproject.entities.Responsible;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Entidade de DTO de pedido de entrada
 *
 * @author Arthur Mendes
 *
 * */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderDTO {

    @NotEmpty
    @NotNull
    private Long orderNumber;
    @NotEmpty
    @NotNull
    private LocalDate  orderDate;

    @NotNull
    private SectionInboundOrderDTO sectionWharehouse;

    @NotNull
    private Long responsibleId;

    private List<BatchDTO> batch = new ArrayList<>();

    public SectionInboundOrderDTO getSectionWharehouse() {
        return sectionWharehouse;
    }

    public void setSectionWharehouse(SectionInboundOrderDTO sectionWharehouse) {
        this.sectionWharehouse = sectionWharehouse;
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

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public void setBatch(List<BatchDTO> batch) {
        this.batch = batch;
    }

    public InboundOrder convert() {
        List<Batch> batches = batch.stream().map(BatchDTO::coverte).collect(Collectors.toList());
        Responsible responsible = new Responsible();
        responsible.setId(this.responsibleId);
        return InboundOrder.builder()
                .orderDate(this.orderDate)
                .orderNumber(this.orderNumber)
                .responsible(responsible)
                .sectionCode(this.sectionWharehouse.getSectionCode())
                .warehouseCode(this.sectionWharehouse.getWarehouseCode())
                .batches(batches)
                .build();
    }

    public static InboundOrderDTO convert(InboundOrder inboundOrder) {
        return InboundOrderDTO.builder()
                .orderDate(inboundOrder.getOrderDate())
                .orderNumber(inboundOrder.getOrderNumber())
                .responsibleId(inboundOrder.getResponsible().getId())
                .sectionWharehouse(new SectionInboundOrderDTO(inboundOrder.getSectionCode(), inboundOrder.getWarehouseCode()))
                .batch(inboundOrder.getBatches().stream().map(BatchDTO::convert).collect(Collectors.toList()))
                .build();
    }
}
