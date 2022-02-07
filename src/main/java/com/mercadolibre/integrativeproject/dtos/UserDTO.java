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
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public User convert() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .build();
    }
}
