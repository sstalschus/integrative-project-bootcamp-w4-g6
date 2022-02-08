package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.AdvertsRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAdvertsService;
import org.springframework.stereotype.Service;

/** Service de Anuncios no carrinho de compras
 *
 * @author Daniel Ramos, Samuel Stalschus
 *
 * */
@Service
public class AdvertsService implements IAdvertsService<Adverts, Long> {

    AdvertsRepository advertsRepository;

    public AdvertsService(AdvertsRepository advertsRepository) {
        this.advertsRepository = advertsRepository;
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
}
