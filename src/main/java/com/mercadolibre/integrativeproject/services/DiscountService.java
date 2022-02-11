package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.dtos.ValueOfDiscountDTO;
import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Discount;
import com.mercadolibre.integrativeproject.enums.DiscountRange;
import com.mercadolibre.integrativeproject.repositories.DiscountRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IDiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolibre.integrativeproject.enums.DiscountRange.getRange;
import static com.mercadolibre.integrativeproject.util.JavaTimeDifference.dateNow;
import static com.mercadolibre.integrativeproject.util.JavaTimeDifference.diferenceBetweenInDays;

/** Service de descontos, requisito 6 Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class DiscountService implements IDiscountService {

    DiscountRepository discountRepository;

    AdvertsService advertsService;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public DiscountService(DiscountRepository discountRepository, AdvertsService advertsService) {
        this.discountRepository = discountRepository;
        this.advertsService = advertsService;
    }

    /** Método usado para criar um desconto, requisito 6 Samuel Stalschus
     *
     * @author Samuel Stalschus
     *
     * */
    public List<Discount> createDiscount() {
        List<Discount> adsOfDiscount = getAdsFromDiscount();
        adsOfDiscount.forEach(this::updateAdvertPrice);
        return discountRepository.saveAll(adsOfDiscount);
    }

    /** Método usado para obter a lista de descontos que precisam ser efetivados
     * Com base em uma analise feita
     *
     * @author Samuel Stalschus
     *
     * @return Retorna uma lista de descontos gerados
     *
     * */
    private List<Discount> getAdsFromDiscount() {
        return advertsService.getAll().stream().filter(this::calculateAdsDiference).collect(Collectors.toList())
                .stream().map(advert -> {
                    List<Discount> discountList = discountRepository.findByBatchId(advert.getBatch().getId());
                    Discount discount = new Discount();
                    if(discountList.size() != 0) {
                        Collections.sort(discountList);
                        discount = discountList.get(discountList.size()-1);
                    }
                    if(discountList.size() != 0 && diferenceBetweenInDays(discount.getCreatedAt(), dateNow()) >=
                            (diferenceBetweenInDays(dateNow(), advert.getBatch().getExpirationDate())/2))
                        return factoryOfDiscount(advert, discount.getDiscountRange());

                     else if(discountList.size() == 0) return factoryOfDiscount(advert, null);
                     else return discount;
                }).collect(Collectors.toList());

    }

    /** Método usado para calcular a diferença de tempo que um lote possui conosco,
     * E qual a sua saída, se a saída for menor com relação ao tempo que ele está conosco será feito um desconto
     *
     * @param advert - Anuncio
     *
     * @author Samuel Stalschus
     *
     * @return Retorna true ou false para informar se esse anuncio precisa de desconto ou não
     *
     * */
    private boolean calculateAdsDiference(Adverts advert) {
        Timestamp now = dateNow();
        return (diferenceBetweenInDays(advert.getBatch().getFabricationDate(), advert.getBatch().getExpirationDate())/2) <=
                (diferenceBetweenInDays(advert.getBatch().getFabricationDate(), now)) &&
                (advert.getBatch().getInitialQuantity()/2 <= advert.getBatch().getQuantity());
    }

    /** Método usado para calcular o valor do desconto, com base no desconto anterior e no range de desconto que podemos oferecer.
     *
     * @param advert - Anuncio
     * @param previousDiscount - Desconto anterior nesse anuncio
     *
     * @author Samuel Stalschus
     *
     * @return Retorna um DTO contendo o novo valor do anuncio e o nível de desconto atual
     *
     * */
    private ValueOfDiscountDTO calculateDiscount(Adverts advert, DiscountRange previousDiscount) {
        Double rangeValue = advert.getPrice().doubleValue() - advert.getBatch().getPricePerUnit().doubleValue();
        ValueOfDiscountDTO valueOfDiscountDTO = new ValueOfDiscountDTO();
        if(previousDiscount == null) {
            valueOfDiscountDTO = ValueOfDiscountDTO.builder()
                    .range(DiscountRange.LOW)
                    .value(new BigDecimal((rangeValue - (rangeValue * getRange(DiscountRange.LOW))) + advert.getBatch().getPricePerUnit().doubleValue()))
                    .build();
        } else if(previousDiscount == DiscountRange.LOW) {
            valueOfDiscountDTO = ValueOfDiscountDTO.builder()
                    .range(DiscountRange.MEDIUM)
                    .value(new BigDecimal((rangeValue - (rangeValue * getRange(DiscountRange.MEDIUM))) + advert.getBatch().getPricePerUnit().doubleValue()))
                    .build();
        } else if(previousDiscount == DiscountRange.MEDIUM) {
            valueOfDiscountDTO = ValueOfDiscountDTO.builder()
                    .range(DiscountRange.HIGH)
                    .value(new BigDecimal((rangeValue - (rangeValue * getRange(DiscountRange.HIGH))) + advert.getBatch().getPricePerUnit().doubleValue()))
                    .build();
        } else if(previousDiscount == DiscountRange.HIGH) {
            valueOfDiscountDTO = ValueOfDiscountDTO.builder()
                    .range(DiscountRange.HIGH)
                    .value(advert.getPrice())
                    .build();
        }
        return  valueOfDiscountDTO;
    }

    /** Método usado para criar um desconto
     *
     * @param advert - Anuncio
     * @param previousDiscount - Desconto anterior nesse anuncio
     *
     * @author Samuel Stalschus
     *
     * @return Retorna desconto criado
     *
     * */
    private Discount factoryOfDiscount(Adverts advert, DiscountRange previousDiscount) {
        ValueOfDiscountDTO valueOfDiscount = calculateDiscount(advert, previousDiscount);
        return Discount.builder()
                .advert(advert)
                .currentPrice(valueOfDiscount.getValue().setScale(2, RoundingMode.HALF_EVEN))
                .discountRange(valueOfDiscount.getRange())
                .previousPrice(advert.getPrice())
                .build();
    }

    /** Método usado para atualizar o anuncio com base em um desconto
     *
     * @param discount - Desconto
     *
     * @author Samuel Stalschus
     *
     * */
    private void updateAdvertPrice(Discount discount) {
        discount.getAdvert().setPrice(discount.getCurrentPrice());
        advertsService.update(discount.getAdvert());
    }

    /** Método usado obter todos os descontos
     *
     * @author Samuel Stalschus
     *
     * @retun Lista de descontos
     *
     * */
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    /** Método usado obter todos os descontos
     *
     * @author Samuel Stalschus
     *
     * @retun Lista de descontos
     *
     * */
    public List<Discount> getByAdvertsId(Long advertId) {
        return discountRepository.findDiscountsByAdvertId(advertId);
    }

    /** Método usado para bloquear a alteração de valor de um anuncio
     *
     * @author Samuel Stalschus
     *
     * @param advertId - Id do anuncio
     *
     * @return Lista de descontos bloqueados
     *
     * */
    public List<Discount> blockAdvert(Long advertId) {
        List<Discount> byAdvertsId = getByAdvertsId(advertId);
        byAdvertsId.forEach(discount -> discount.setDiscountRange(DiscountRange.HIGH));
         return discountRepository.saveAll(byAdvertsId);
    }
}
