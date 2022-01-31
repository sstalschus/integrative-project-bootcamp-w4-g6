package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.ISectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
/** Service de Setor
 * @author Lorraine Mendes
 * */


@Service
public class SectorService implements ISectorService<Sector, Long> {

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private BatchService batchService;

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
        return sectorRepository.findById(sectorId).orElseThrow(()->new NotFoundException("Sector not found"));
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
    public void update(Sector sector) {
        sectorRepository.setSectorInfoById(sector.getTemperature(), sector.getCapacity(), sector.getName(), sector.getId());
    }

    @Override
    public void delete(Long sectorId) {
        try {
            sectorRepository.deleteById(sectorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sector getValidSectorOnStorage(Long id, Storage storage) {
        Sector sector = storage.getSectorsList().stream()
                .filter(sec -> sec.getId().equals(id))
                .findFirst().orElse(null);

        if (sector == null){
            throw new NotFoundException("Sector not found");
        }
        return sector;
    }


    public Double calcVolumn(Sector sector) {
        return batchService.calcVolumn(sector.getLots());
    }

    public boolean hasSectorCapacity(List<Batch> batches, Sector sector) {
        Double volumnBatches = batchService.calcVolumn(batches);
        Double usedCapacitySector = calcVolumn(sector);
        Double newUsedCapacitySector = volumnBatches + usedCapacitySector;
        return sector.getCapacity() >= newUsedCapacitySector;
    }


}
