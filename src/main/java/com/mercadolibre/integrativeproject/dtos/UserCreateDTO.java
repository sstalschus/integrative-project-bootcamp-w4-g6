package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreateDTO {

    private String name;
    private String email;
    private String password;

    public User convert() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
    }

}
