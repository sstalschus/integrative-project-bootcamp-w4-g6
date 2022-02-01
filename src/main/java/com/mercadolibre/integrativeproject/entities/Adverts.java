package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Zipcode street district city state country
public class Adverts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}