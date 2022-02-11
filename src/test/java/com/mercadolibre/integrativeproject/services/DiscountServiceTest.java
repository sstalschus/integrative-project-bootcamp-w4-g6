package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Discount;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.DiscountRange;
import com.mercadolibre.integrativeproject.repositories.DiscountRepository;
import com.mercadolibre.integrativeproject.util.JavaTimeDifference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.integrativeproject.util.JavaTimeDifference.toTimestamp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

    @Mock
    DiscountRepository mockDiscountRepository;

    @Mock
    AdvertsService mockAdvertsService;

    @InjectMocks
    DiscountService discountService;

    @Test
    void createDiscount() {
        // Arrange
        Product product = Product.builder().id(1L).build();

        Batch batch = new Batch();
        batch.setId(1L);
        batch.setInitialQuantity(10L);
        batch.setQuantity(10L);
        batch.setProduct(product);
        batch.setExpirationDate(toTimestamp("2022-02-20"));
        batch.setFabricationDate(toTimestamp("2021-10-21"));
        batch.setPricePerUnit(new BigDecimal(100));

        Adverts adverts = Adverts.builder().id(1L).price(new BigDecimal(300)).batch(batch).build();
        List<Adverts> advertsList = new ArrayList<>(Arrays.asList(adverts));

        Discount discount = Discount.builder().id(1L).advert(adverts).discountRange(DiscountRange.LOW).build();
        List<Discount> discountList = new ArrayList<>();

        when(mockAdvertsService.getAll()).thenReturn(advertsList);
        when(mockDiscountRepository.findByBatchId(adverts.getBatch().getId())).thenReturn(new ArrayList<>());
        when(mockAdvertsService.update(adverts)).thenReturn(null);
        lenient().when(mockDiscountRepository.saveAll(discountList)).thenReturn(discountList);

        // Act
        List<Discount> discountCreated = discountService.createDiscount();

        // Assert
        assertEquals(discountList.size(), discountCreated.size());

    }

    @Test
    void getAll() {
        // Arrange
        Discount discount = Discount.builder().id(1L).discountRange(DiscountRange.LOW).build();
        List<Discount> discountList = new ArrayList<>(Arrays.asList(discount));

        when(mockDiscountRepository.findAll()).thenReturn(discountList);

        // Act

        List<Discount> all = discountService.getAll();

        // Assert

        assertEquals(discountList.get(0).getId(), all.get(0).getId());
    }

    @Test
    void getByAdvertsId() {
        // Arrange
        Discount discount = Discount.builder().id(1L).discountRange(DiscountRange.LOW).build();
        List<Discount> discountList = new ArrayList<>(Arrays.asList(discount));

        when(mockDiscountRepository.findDiscountsByAdvertId(1L)).thenReturn(discountList);

        // Act

        List<Discount> all = discountService.getByAdvertsId(1L);

        // Assert

        assertEquals(discountList.get(0).getId(), all.get(0).getId());
    }

    @Test
    void blockAdvert() {
        // Arrange
        Discount discount = Discount.builder().id(1L).discountRange(DiscountRange.LOW).build();
        List<Discount> discountList = new ArrayList<>(Arrays.asList(discount));

        when(mockDiscountRepository.findDiscountsByAdvertId(1L)).thenReturn(discountList);
        when(mockDiscountRepository.saveAll(discountList)).thenReturn(discountList);

        // Act

        List<Discount> all = discountService.blockAdvert(1L);

        // Assert

        assertEquals(discountList.get(0).getId(), all.get(0).getId());
    }
}