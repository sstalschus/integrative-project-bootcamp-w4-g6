package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/** Service para a entidade de registro de Responsible.
 *
 * @author Jefferson Froes
 *
 * */
@Service
public class ResponsibleService implements IResponsibleService<Responsible, Long> {

    @Autowired
    SectorService sectorService;

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
     */
    @Override
    public Responsible create(Responsible responsible, Long sectorId) {
        Sector sector = sectorService.getById(sectorId);
        responsible.setSector(sector);
        return responsibleRepository.save(responsible);
    }

    @Override
    public void update(Long responsibleId, Long sectorId) {
        //Sector sector = sectorService.getById(sectorId);
        responsibleRepository.setResponsibleChangeSector(responsibleId, sectorId);
    }

    /**
     * Método usado para buscar um registro de responsible pelo id.
     *
     * @param responsibleId - id para busca.
     * @return Registro de responsible por id.
     * @author Jefferson Froes
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
     */
    @Override
    public void delete(Long responsibleId) {
        responsibleRepository.deleteById(responsibleId);
    }
}