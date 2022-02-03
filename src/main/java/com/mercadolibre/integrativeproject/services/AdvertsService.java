package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AdvertsRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAdvertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertsService implements IAdvertsService<Adverts, Long> {

    @Autowired
    private AdvertsRepository advertsRepository;

    @Override
    public Adverts create(Adverts adverts) {
        return advertsRepository.save(adverts);
    }

    @Override
    public Adverts getById(Long avertsId) {
        return advertsRepository.findById(avertsId).orElseThrow(() -> new NotFoundException("Advert not found."));
    }

    @Override
    public Adverts update(Adverts averts) {
        return advertsRepository.save(averts);
    }

    @Override
    public void delete(Long advertsId) {
        advertsRepository.deleteById(advertsId);
    }
}
