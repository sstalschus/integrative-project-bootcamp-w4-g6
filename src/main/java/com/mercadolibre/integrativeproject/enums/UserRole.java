package com.mercadolibre.integrativeproject.enums;

public enum UserRole {

    MEMBER(0),
    ADMIN(1);

    private int code;

    private UserRole(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserRole valueOf(int code) throws IllegalAccessException {
        for(UserRole value : UserRole.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalAccessException("Invalid OrderStatus code");
    }
}
