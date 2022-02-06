package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/** Service para a entidade de registro de Responsible.
 *
 * @author Jefferson Froes
 *
 * */
@Service
public class ResponsibleService implements ICrudServiceInterface<Responsible, Long> {

//    @Autowired
//    SectorService sectorService;

    ResponsibleRepository responsibleRepository;

    public ResponsibleService(ResponsibleRepository responsibleRepository) {
        this.responsibleRepository = responsibleRepository;
    }

    /**
     * Método usado para criar um novo registro de Responsible.
     *
     * @param responsible - registro de responsible.
     * @return Registro de responsible criado.
     * @author Jefferson Froes
     *
     */
    @Override
    public Responsible create(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    @Override
    public Responsible update(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    /**
     * Método usado para buscar um registro de responsible pelo id.
     *
     * @param responsibleId - id para busca.
     * @return Registro de responsible por id.
     * @author Jefferson Froes
     *
     */
    @Override
    public Responsible getById(Long responsibleId) {
        return responsibleRepository.findById(responsibleId).orElseThrow(() -> new NotFoundException("Id not Found."));
    }

    /**
     * Método usado para pegar todos os registros de responsible.
     *
     * @return Lista com os registros de responsible.
     * @author Jefferson Froes
     *
     */
    @Override
    public List<Responsible> getAll() {
        return responsibleRepository.findAll();
    }


    /**
     * Método usado para deletar o registro responsible.
     *
     * @param responsibleId - id do objeto a ser deletado
     * @author Jefferson Froes.
     *
     */
    @Override
    public void delete(Long responsibleId) {
        responsibleRepository.deleteById(responsibleId);
    }

//    public Responsible changeSectorResponsible(Long responsibleId, Long sectorId) {
//
//        Responsible newResponsible = responsibleRepository.getById(responsibleId);
//        Sector sector = sectorService.getById(sectorId);
//        newResponsible.setSector(sector);
//        sector.setResponsible(newResponsible);
//        sector.getResponsible().setSector(null);
//        update(sector.getResponsible());
//        update(newResponsible);
//        sectorService.update(sector);
//
//        return newResponsible;
//    }
}
