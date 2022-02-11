package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.ProductType;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.util.EnumValidator;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Entidade de DTO do Produto
 *
 * @author Arthur Amorim, Samuel Stalschus, Lorraine Mendes
 *
 * */
public class ProductDTO {

    private Long id;
    @NotNull(message = "Product name cannot null")
    @NotEmpty(message = "Product name cannot empty")
    @NotBlank(message = "Product name cannot blank")
    @Size(max = 30, min = 2, message = "Product name need contains 2 at 30 characters")
    private String name;

    private Double volumn;

    @EnumValidator(
            enumClazz = StorageType.class,
            message = "The category must be type: FS (Fresh), RF (Chilled) or FF (Frozen)."
    )
    private String category;

    @EnumValidator(
            enumClazz = ProductType.class,
            message = "The product type must be: VEGAN (Vegano) or VEGETARIAN (Vegetariano)."
    )
    private String productType;

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

    public Double getVolumn() {
        return volumn;
    }

    public void setVolumn(Double volumn) {
        this.volumn = volumn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /** DTO de produto, para o tipo de produto. TASK 6.
     *
     * @author Lorraine Mendes
     *
     * @return tipo de produto
     * */
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Product convert() {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .volumn(this.volumn)
                .category(StorageType.valueOf(category))
                .productType(ProductType.valueOf(productType)).build();
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
