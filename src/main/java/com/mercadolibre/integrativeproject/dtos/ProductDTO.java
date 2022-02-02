package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    @Id
    private Long id;
    @NotNull(message = "Product name cannot null")
    @NotEmpty(message = "Product name cannot empty")
    @NotBlank(message = "Product name cannot blank")
    @Size(max = 30, min = 2, message = "Product name need contains 2 at 30 characters")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product convert() {
        return Product.builder()
                .id(this.id)
                .name(this.name).build();
    }

    /** Controller de registro de supplier.
     *
     * @author Jefferson Froes, Samuel Stalschus.
     *
     * @return  retorna um ProductDTO.
     * */
    public static ProductDTO convert(Product product){
        ModelMapper modelMapper = new ModelMapper();
       return modelMapper.map(product, ProductDTO.class);
    }

}
