package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para a entidade de registro de Responsible.
 *
 * @author Jefferson Froes
 */
@Service
public class ResponsibleService implements ICrudServiceInterface<Responsible, Long> {

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
    public Responsible create(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    /**
     * Método usado para atualizar o registro responsible.
     *
     * @param responsible - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Jefferson Froes, Arthur Lima
     *
     */
    @Override
    public Responsible update(Responsible responsible) throws NotFoundException {
        Responsible responsibleId = getById(responsible.getId());
        return responsibleRepository.save(responsible);
    }

    /**
     * Método usado para buscar um registro de responsible pelo id.
     *
     * @param responsibleId - id para busca.
     * @return Registro de responsible por id.
     * @author Jefferson Froes
     */
    @Override
    public Responsible getById(Long responsibleId) throws NotFoundException {
        return responsibleRepository.findById(responsibleId)
                .orElseThrow(() -> new NotFoundException("Responsible id not Found."));
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
     * @throws RepositoryException - trata erro ao deletar responsible.
     * @author Jefferson Froes.
     */
    @Override
    public void delete(Long responsibleId) throws RepositoryException {
        try {
            responsibleRepository.deleteById(responsibleId);
        }catch (Exception e){
            throw new RepositoryException("Error by delete Responsible");
        }
    }
}
