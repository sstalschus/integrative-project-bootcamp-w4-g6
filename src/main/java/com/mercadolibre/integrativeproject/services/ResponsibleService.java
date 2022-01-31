package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Service para a entidade de registro de Responsible.
 *
 * @author Jefferson Froes
 *
 * */
@Service
public class ResponsibleService implements ISupplierService<Responsible, Long> {

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
    public Responsible create(Responsible responsible) {
        try{
            return responsibleRepository.save(responsible);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Responsible responsible, Long responsibleID) {
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
