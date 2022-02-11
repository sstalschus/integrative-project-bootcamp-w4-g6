package com.mercadolibre.integrativeproject.enums;

/** Entidade de enum para qualificar o nível do desconto, Requisito 6 Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
public enum DiscountRange {

    LOW, MEDIUM, HIGH;

    public static Double getRange(DiscountRange d) {
        if(d == DiscountRange.LOW) return 20.0/100;
        else if(d == DiscountRange.MEDIUM) return 40.0/100;
        else if(d == DiscountRange.HIGH) return 80.0/100;
        else return 1.0;
    }
}
