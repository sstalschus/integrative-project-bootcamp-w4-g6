package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.services.interfaces.BathServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/** Service do lote
 *
 * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
 *
 * */
@Service
public class BatchService implements ICrudServiceInterface<Batch, Long>, BathServiceInterface {

    private final BatchRepository batchRepository;

    private final SectorService sectorService;

    public BatchService(BatchRepository batchRepository, @Lazy SectorService sectorService) {
        this.batchRepository = batchRepository;
        this.sectorService = sectorService;
    }

    /** Método usado para criar um novo lote
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     * @param  batch - Lote
     *
     * @return Lote
     *
     * */
    @Override
    public Batch create(Batch batch) {
        return batchRepository.save(batch);
    }

    public List<Batch> create(List<Batch> batches){
        return batchRepository.saveAll(batches);
    }

    /** Método usado para obter um Lote.
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     * @param  batchId - Id do batch.
     *
     * @return Anúncio que tenha o batchd informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Batch getById(Long batchId) {
        return batchRepository.findById(batchId).orElse(null);
    }

    /**
     * Método usado para pegar todos os registros de batch.
     *
     * @author Arthur Amorim
     *
     * @return Lista com os registros de batch.
     *
     */
    @Override
    public List<Batch> getAll() {
        return batchRepository.findAll();
    }

    /**
     * Método usado para atualizar o registro batch.
     *
     * @param batch - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     */
    @Override
    public Batch update(Batch batch) {
        return batchRepository.save(batch);
    }

    /**
     * Método usado para deletar o registro batch.
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus.
     *
     * @param batchId - id do objeto a ser deletado
     *
     * @throws RepositoryException - trata erro ao deletar batch.
     */
    @Override
    public void delete(Long batchId) {
        batchRepository.deleteById(batchId);
    }

    @Override
    public Double calcVolumn(List<Batch> batches) {
        return batches.stream().mapToDouble(batchOnSector ->
                batchOnSector.getQuantity() * batchOnSector.getProduct().getVolumn()).sum();
    }

    /** Método usado para obter uma lista dos lotes por setor
     *
     * @author Samuel Stalschus
     *
     * @param sectorId - numero de dias dentro do range de expiração de setor
     *
     * @return Lista de lotes com base na data de validade
     *
     * */
    public List<Batch> getBatchesByDueDate(Integer numberOfDays, Long sectorId){
        Sector sector = sectorService.getById(sectorId);
        List<Batch> batches = sector.getLots();

        List<Batch> batchesFilterList = filterBacthsByDueDate(numberOfDays, batches);

        batchesFilterList.sort(Comparator.comparing(Batch::getExpirationDate));
        return batchesFilterList;
    }

    /** Método usado para obter uma lista dos lotes pedidos que vão expirar em uma determinada quantidade de dias de uma determinada categoria de produtos
     *
     * @author Samuel Stalschus
     *
     * @param numberOfDays - numero de dias dentro do range de expiração de produto
     * @param category - categoria do produto
     * @param asc - ordenar lista de lotes em ordem ascedente
     *
     * @return Lista de lotes com base na data de validade e categoria
     *
     * */
    public List<Batch> getBatchesByProductCategoyAndDueDate(Integer numberOfDays, StorageType category, boolean asc){
        List<Batch> batchs = batchRepository.findAll()
                .stream()
                .filter(batch -> batch.getProduct().getCategory().equals(category))
                .collect(Collectors.toList());

        batchs = filterBacthsByDueDate(numberOfDays, batchs);

        if(asc) batchs.sort(Comparator.comparing(Batch::getExpirationDate));
        else batchs.sort(Comparator.comparing(Batch::getExpirationDate).reversed());

        return  batchs;
    }

    /** Método usado para obter uma lista de lotes e filtrar com base na data de expiração desejada pelo client
     *
     * @author Samuel Stalschus
     *
     * @param numberOfDays - numero de dias dentro do range de expiração de produto
     * @param batches - categoria do produto
     *
     * @return Lista de lotes que estão com a data de expiração dentro do range de dias informados
     *
     * */
    private List<Batch> filterBacthsByDueDate(Integer numberOfDays, List<Batch> batches) {
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        Timestamp expiredDate = Timestamp.valueOf(now.toLocalDateTime().plusDays(numberOfDays));
        return batches.stream().filter(batch -> batch.getExpirationDate().before(expiredDate)).collect(Collectors.toList());
    }
}
