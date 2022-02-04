package com.mercadolibre.integrativeproject.dtos;

public class SectionInboundOrderDTO {

    private Long sectionCode;
    private Long warehouseCode;

    public SectionInboundOrderDTO() {
    }

    public SectionInboundOrderDTO(Long sectionCode, Long warehouseCode) {
        this.sectionCode = sectionCode;
        this.warehouseCode = warehouseCode;
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
}
