package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponsibleService implements ICrudServiceInterface<Responsible, Long> {

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @Override
    public Responsible create(Responsible responsible) {
        try{
            return responsibleRepository.save(responsible);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Responsible getById(Long responsibleId) {
        try {
            return responsibleRepository.findById(responsibleId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Responsible> getAll() {
        try {
            return responsibleRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Responsible update(Responsible responsible) {
        try {
            return responsibleRepository.save(responsible);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long responsibleId) {
        try{
            responsibleRepository.deleteById(responsibleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
