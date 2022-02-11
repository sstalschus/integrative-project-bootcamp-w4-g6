package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.entities.Purchase;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.enums.StatusPurchase;
import com.mercadolibre.integrativeproject.enums.StorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

/** Entidade de DTO da Compra
 *
 * @author Daniel Ramos
 *
 * */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;

    @NotNull(message = "O carrinho não pode estar vazio")
    private Long shoppingCart;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Long shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Purchase convert() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(this.shoppingCart);
        if (this.status != null){
            return Purchase.builder()
                    .id(this.id)
                    .shoppingCart(shoppingCart)
                    .status(StatusPurchase.valueOf(this.status.toUpperCase())).build();
        }else{
            return Purchase.builder()
                    .id(this.id)
                    .shoppingCart(shoppingCart)
                    .build();
        }


    }

    /** Controller de registro de purchase.
     *
     * @author Daniel Ramos
     *
     * @return  retorna um PurchaseDTO.
     * */
    public static PurchaseDTO convert(Purchase purchase){
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .shoppingCart(purchase.getId())
                .status(purchase.getStatus().name())
                .build();
    }
}
