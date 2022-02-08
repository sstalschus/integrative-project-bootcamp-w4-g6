package com.mercadolibre.integrativeproject.entities;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Entidade de Address
 *
 * @author Daniel Ramos, Arthur Amorim, Jeffersom Froes
 *
 * */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Zipcode street district city state country
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String zipCode;

    @NotNull
    @Column
    private String street;

    @NotNull
    @Column
    private String district;

    @NotNull
    @Column
    private String city;

    @NotNull
    @Column
    private String state;

    @NotNull
    @Column
    private String country;
}
