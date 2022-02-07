package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service para a entidade do Produto.
 *
 * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
 *
 * */
@Service
public class ProductService implements ICrudServiceInterface<Product, Long> {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** Método usado para criar um novo Produto
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     * @param  product - Produto
     *
     * @return Repositório do Produto
     *
     * */
    @Override
    public Product create(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return null;
        }
        if (product.getId() != null && product.getId() > 0) {
            Product productOnDatabase = getById(product.getId());
            if (productOnDatabase != null) {
                if (product.getName().equals(productOnDatabase.getName())) {
                    return productOnDatabase;
                } else {
                    return null;
                }
            }
        } else {
            try {
                return productRepository.save(product);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    /** Método usado para obter um Produto.
     *
     * @author Arthur Amorim.
     *
     * @param  productId - Id do product.
     *
     * @return Produto que tenha o productId informado.
     *
     * */
    @Override
    public Product getById(Long productId) {

        return productRepository.findById(productId).orElse(null);
    }

    /** Método usado para pegar todos os produtos.
     *
     * @author Arthur Amorim, Jefferson Froes.
     *
     * @return Lista com os produtos
     *
     * */
    @Override
    public List<Product> getAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.size() == 0) throw new NotFoundException("Products list not exist");
        return productList;
    }

    /** Método usado para pegar as categoria do produto.
     *
     * @author Arthur Amorim, Lorraine Mendes.
     *
     * @return Lista da categoria do produto
     *
     * @throws NotFoundException
     *
     * */
    public List<Product> getByCategory(StorageType queryType){
        List<Product> productList = productRepository.findByCategory(queryType);
        if (productList.size() == 0) throw new NotFoundException("Product category not found");
        return productList;
    }

    /**
     * Método usado para atualizar o registro product.
     *
     * @param product - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Arthur Amorim.
     *
     */
    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    /**
     * Método usado para deletar o registro product.
     *
     * @author Lorraine Mendes, Arthur Amorim.
     *
     * @param productId - id do objeto a ser deletado
     *
     */
    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
