package com.mercadolibre.integrativeproject.entities;

public class MessageModel {

    private Long sectorId;
    private Double temperature;
    private String status;
    private Double minimumTemperatureNecessarie;


    public Double getMinimumTemperatureNecessarie() {
        return minimumTemperatureNecessarie;
    }

    public void setMinimumTemperatureNecessarie(Double minimumTemperatureNecessarie) {
        this.minimumTemperatureNecessarie = minimumTemperatureNecessarie;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "sectorId=" + sectorId +
                ", temperature=" + temperature +
                ", status='" + status  +
                '}';
    }
}
