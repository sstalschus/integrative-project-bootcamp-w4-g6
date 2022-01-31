package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
/** Service de Setor
 * @author Lorraine Mendes
 * */

@Service
public class SectorService implements ICrudServiceInterface<Sector, Long> {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Sector create(Sector sector){
        try{
            return sectorRepository.save(sector);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sector getById(Long sectorId) {
        try {
            return sectorRepository.findById(sectorId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Sector> getAll() {
        try {
            return sectorRepository.findAll();
        } catch (Exception e)  {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Sector update(Sector sector) {
        try {
            return sectorRepository.save(sector);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long sectorId) {
        try {
            sectorRepository.deleteById(sectorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
