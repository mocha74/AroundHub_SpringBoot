package studio.thinkground.aroundhub.Dao;

import studio.thinkground.aroundhub.entity.ProductEntity;

public interface ProductDao {

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String productId);
}
