package studio.thinkground.aroundhub.Dao.impl;

import studio.thinkground.aroundhub.Dao.ProductDao;
import studio.thinkground.aroundhub.entity.ProductEntity;
import studio.thinkground.aroundhub.repository.ProductRepository;

public class ProductDAOImpl implements ProductDao {

    ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getReferenceById(productId);
        return productEntity;
    }
}
