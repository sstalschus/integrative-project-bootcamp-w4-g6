package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.AdvertsRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAdvertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Service de Anuncios no carrinho de compras
 *
 * @author Daniel Ramos, Samuel Stalschus
 *
 * */
@Service
public class AdvertsService implements IAdvertsService<Adverts, Long> {

    private AdvertsRepository advertsRepository;

    private BatchService batchService;

    private ResponsibleService responsibleService;

    private SectorService sectorService;

    public AdvertsService(AdvertsRepository advertsRepository, BatchService batchService, ResponsibleService responsibleService, SectorService sectorService) {
        this.advertsRepository = advertsRepository;
        this.batchService = batchService;
        this.responsibleService = responsibleService;
        this.sectorService = sectorService;
    }

    /** Método usado para criar um novo anuncio
     *
     * @author Daniel Ramos, Samuel Stalschus
     *
     * @param  adverts - Anuncio
     *
     * @return Anuncio
     *
     * */
    @Override
    public Adverts create(Adverts adverts) {
        Batch batch = batchService.getById(adverts.getBatch().getId());
        if (batch == null) {
            throw new NotFoundException("Batch not found");
        }
        adverts.setBatch(batch);
        Responsible responsible = responsibleService.getById(adverts.getResponsible().getId());

        if (responsible == null) {
            throw new NotFoundException("Responsible not found");
        }
        List<Sector> sectors = getSectorByResponsibleId(responsible.getId());
        if (sectors == null || sectors.isEmpty()) {
            throw new NotFoundException("Sector not found or not responsible");
        }
        List<Batch> batches = new ArrayList<>();
        sectors.forEach(sector -> {
            batches.addAll(sector.getLots());
        });
        if (batches.isEmpty()) {
            throw new NotFoundException("Lot not found in the responsible sectors");
        }
        adverts.setResponsible(responsible);
        return advertsRepository.save(adverts);
    }

    /** Método usado para obter um Anúncio.
     *
     * @author Daniel Ramos, Samuel Stalschus
     *
     * @param  advertsId - Id do adverts.
     *
     * @return Anúncio que tenha o advertsId informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Adverts getById(Long advertsId) throws NotFoundException {
        return advertsRepository.findById(advertsId).orElseThrow(() -> new NotFoundException("Advert not found."));
    }

    /**
     * Método usado para atualizar o registro adverts.
     *
     * @param adverts - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Daniel Ramos, Samuel Stalschus
     *
     */
    @Override
    public Adverts update(Adverts adverts) {
        return advertsRepository.save(adverts);
    }

    /**
     * Método usado para deletar o registro adverts.
     *
     * @author Daniel Ramos, Samuel Stalschus.
     *
     * @param advertsId - id do objeto a ser deletado
     *
     * @throws RepositoryException - trata erro ao deletar adverts.
     */
    @Override
    public void delete(Long advertsId) throws RepositoryException {
        try{
            advertsRepository.deleteById(advertsId);

        } catch(Exception e) {
            throw new RepositoryException("Error by delete adverts");
        }
    }

    public List<Adverts> getAll(){
        return advertsRepository.findAll();
    }

    public List<Sector> getSectorByResponsibleId(Long responsibleId){
        return sectorService.getSectorByResponsible(responsibleId);
    }
}
