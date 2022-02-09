package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.dtos.AddProductInShoppingCar;
import com.mercadolibre.integrativeproject.dtos.CreateShoppingCartDTO;
import com.mercadolibre.integrativeproject.dtos.PucharseOrderDTO;
import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.accessibility.AccessibleResourceBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    ShoppingCartRepository mockShoppingCartRepository;

    @Mock
    CustomerService mockCustomerService;

    @Mock
    AdvertsInShoppingCartService mockAdvertsInShoppingCartService;

    @Mock
    AdvertsService mockAdvertsService;

    @InjectMocks
    ShoppingCartService shoppingCartService;

    @Test
    void getByIdSuccess() {
        // Arrange
        ShoppingCart shoppingCart = ShoppingCart.builder().id(1L).build();
        when(mockShoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));

        // Act
        ShoppingCart shoppingCartCreated = shoppingCartService.getById(1L);

        // Assert
        assertEquals(shoppingCart.getId(), shoppingCartCreated.getId());
    }

    @Test
    void getByIdError() {
        // Arrange
        when(mockShoppingCartRepository.findById(1L)).thenThrow(new NotFoundException("Shopping Cart not found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> shoppingCartService.getById(1L));
        assertEquals("Shopping Cart not found", exception.getMessage());
    }

    @Test
    void getAll() {
        // Arrange
        ShoppingCart shoppingCart1 = ShoppingCart.builder().id(1L).build();
        ShoppingCart shoppingCart2 = ShoppingCart.builder().id(2L).build();

        List<ShoppingCart> list = new ArrayList<>(Arrays.asList(shoppingCart1, shoppingCart2));
        when(mockShoppingCartRepository.findAll()).thenReturn(list);

        // Act
        List<ShoppingCart> listCreatd = shoppingCartService.getAll();

        // Assert
        assertEquals(list.size(), listCreatd.size());
        assertEquals(list.get(0).getId(), listCreatd.get(0).getId());
    }

    @Test
    void getProductsByShoppingCart() {
        // Arrange
        AdvertsInShoppingCart adverts1 = AdvertsInShoppingCart.builder().id(1L).build();
        AdvertsInShoppingCart adverts2 = AdvertsInShoppingCart.builder().id(2L).build();

        List<AdvertsInShoppingCart> list = new ArrayList<>(Arrays.asList(adverts1, adverts2));

        ShoppingCart shoppingCart = ShoppingCart.builder().advertsInShoppingCart(list).id(1L).build();
        when(mockShoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));

        // Act
        List<AdvertsInShoppingCart> productsByShoppingCart = shoppingCartService.getProductsByShoppingCart(1L);

        // Assert
        assertEquals(list.size(), productsByShoppingCart.size());
        assertEquals(list.get(0).getId(), productsByShoppingCart.get(0).getId());
    }

    @Test
    void updateOrdersCart() {
        // Arrange
        UpdateCartShopping updateCartShopping = new UpdateCartShopping();
        updateCartShopping.setCartId(1L);
        updateCartShopping.setOrderToDelete(null);
        updateCartShopping.setAdvertsInShoppingCart(null);

        Adverts adverts = Adverts.builder().id(1L).batch(new Batch()).price(BigDecimal.valueOf(10.0)).build();
        AdvertsInShoppingCart advertsInShoppingCart1 = AdvertsInShoppingCart.builder().id(2L).advert(adverts).build();
        List<AdvertsInShoppingCart> advertsInShoppingCartList = new ArrayList<>(Arrays.asList(advertsInShoppingCart1));
        ShoppingCart shoppingCart = ShoppingCart.builder().advertsInShoppingCart(advertsInShoppingCartList).id(1L).build();

        when(mockShoppingCartRepository.getById(updateCartShopping.getCartId())).thenReturn(shoppingCart);

        // Act
        ShoppingCart shoppingCartCreated = shoppingCartService.updateOrdersCart(updateCartShopping);

        // Assert
        assertEquals(updateCartShopping.getCartId(), shoppingCartCreated.getId());
    }

    @Test
    void updateOrdersCartDecrement() {
        // Arrange
        UpdateCartShopping updateCartShopping = new UpdateCartShopping();
        updateCartShopping.setCartId(1L);
        updateCartShopping.setOrderToDelete(1L);

        Adverts adverts = Adverts.builder().id(1L).batch(new Batch()).price(BigDecimal.valueOf(10.0)).build();
        AdvertsInShoppingCart advertsInShoppingCart1 = AdvertsInShoppingCart.builder().id(2L).advert(adverts).build();
        updateCartShopping.setAdvertsInShoppingCart(advertsInShoppingCart1);
        List<AdvertsInShoppingCart> advertsInShoppingCartList = new ArrayList<>(Arrays.asList(advertsInShoppingCart1));
        ShoppingCart shoppingCart = ShoppingCart.builder().advertsInShoppingCart(advertsInShoppingCartList).id(1L).build();
        ShoppingCartService mockShoppingCartService = Mockito.mock(ShoppingCartService.class);

        when(mockShoppingCartRepository.getById(updateCartShopping.getCartId())).thenReturn(shoppingCart);
        lenient().when(mockShoppingCartService.removeOrderOnCart(shoppingCart, 1L)).thenReturn(advertsInShoppingCartList);
        lenient().when(mockShoppingCartService.addOrderOnCart(advertsInShoppingCart1, shoppingCart)).thenReturn(shoppingCart);

        // Act
        ShoppingCart shoppingCartCreated = shoppingCartService.updateOrdersCart(updateCartShopping);

        // Assert
        assertEquals(updateCartShopping.getCartId(), shoppingCartCreated.getId());
    }

    @Test
    void decrementProductByList() {
    }

    @Test
    void create() {
        // Arrange
        AddProductInShoppingCar addProductInShoppingCar = AddProductInShoppingCar.builder().advertId(1L).quantity(10).build();
        List<AddProductInShoppingCar> products = new ArrayList<AddProductInShoppingCar>(Arrays.asList(addProductInShoppingCar));
        CreateShoppingCartDTO createShoppingCartDTO = CreateShoppingCartDTO.builder().buyerId(1L).products(products).build();
        PucharseOrderDTO pucharseOrderDTO = PucharseOrderDTO.builder().purcharseOrder(createShoppingCartDTO).build();
        Customer customer = Customer.builder().id(2L).build();
        Batch batch = new Batch();
        Adverts adverts = Adverts.builder().id(1L).batch(batch).price(BigDecimal.valueOf(10.0)).build();
        AdvertsInShoppingCart advertsInShoppingCart1 = AdvertsInShoppingCart.builder().id(2L).advert(adverts).build();
        List<AdvertsInShoppingCart> advertsInShoppingCartList = new ArrayList<>(Arrays.asList(advertsInShoppingCart1));
        ShoppingCart shoppingCart = ShoppingCart.builder().advertsInShoppingCart(advertsInShoppingCartList).id(1L).build();
        advertsInShoppingCart1.setShoppingCart(shoppingCart);
        ShoppingCartService mockShoppingCartService = Mockito.mock(ShoppingCartService.class);

        when(mockCustomerService.getById(pucharseOrderDTO.getPurcharseOrder().getBuyerId())).thenReturn(customer);
        when(mockAdvertsService.getById(pucharseOrderDTO.getPurcharseOrder().getProducts().get(0).getAdvertId())).thenReturn(adverts);
        lenient().when(mockShoppingCartService.generateShoppingCarAndSave(customer)).thenReturn(shoppingCart);
        lenient().when(mockShoppingCartService.generateAdvertsInShoppingCartsAndSave(pucharseOrderDTO, shoppingCart)).thenReturn(advertsInShoppingCartList);

        // Act
        shoppingCartService.create(pucharseOrderDTO);

        // Assert
        assertEquals(shoppingCart.getAdvertsInShoppingCart().get(0).getAdvert().getPrice(), shoppingCart.getAdvertsInShoppingCart().get(0).getAdvert().getPrice());
    }
}