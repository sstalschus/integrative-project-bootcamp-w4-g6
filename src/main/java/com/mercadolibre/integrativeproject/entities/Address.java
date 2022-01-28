package com.mercadolibre.integrativeproject.entities;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Zipcode street district city state country
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String zipCode;

    @Column
    private String street;

    @NotNull
    @ManyToMany
    private String district;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;


}
