package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
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
        try {
            return advertsRepository.findById(avertsId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Adverts update(Adverts averts) {
        try {
            return advertsRepository.save(averts);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long advertsId) {
        try {
            advertsRepository.deleteById(advertsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
