package studio.thinkground.aroundhub.data.dao;

import studio.thinkground.aroundhub.data.entity.ProductEntity;

public interface ProductDAO {
    public ProductEntity saveProduct(ProductEntity productEntity);

    public ProductEntity getProduct(String productId);
}
