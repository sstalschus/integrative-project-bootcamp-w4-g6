package com.mercadolibre.integrativeproject.services.interfaces;

import com.mercadolibre.integrativeproject.entities.Batch;

import java.util.List;

/** Interface para BathService
 *
 * @author Arthur Amorim
 *
 * */
public interface BathServiceInterface {

    public Double calcVolumn(List<Batch> batches);

}
