package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponsibleService(ResponsibleRepository responsibleRepository){
        this.responsibleRepository = responsibleRepository;
    }

    /** Método usado para criar um novo registro de Responsible.
     *
     * @author Jefferson Froes
     *
     * @param responsible - registro de responsible.
     *
     * @return Registro de responsible criado.
     *
     * */
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

    /** Método usado para buscar um registro de responsible pelo id.
     *
     * @author Jefferson Froes
     *
     * @param responsibleId - id para busca.
     *
     * @return Registro de responsible por id.
     *
     * */
    @Override
    public Responsible getById(Long responsibleId) {
            return responsibleRepository.findById(responsibleId).orElseThrow(() -> new NotFoundException("Id not Found."));
    }

    /** Método usado para pegar todos os registros de responsible.
     *
     * @author Jefferson Froes
     *
     * @return Lista com os registros de responsible.
     *
     * */
    @Override
    public List<Responsible> getAll() {
        try {
            return responsibleRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    /** Método usado para deletar o registro responsible.
     *
     * @author Jefferson Froes.
     *
     * @param responsibleId - id do objeto a ser deletado
     *
     * */
    @Override
    public void delete(Long responsibleId) {
        try{
            responsibleRepository.deleteById(responsibleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
