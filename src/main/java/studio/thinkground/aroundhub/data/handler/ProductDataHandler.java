package studio.thinkground.aroundhub.data.handler;

import studio.thinkground.aroundhub.data.entity.ProductEntity;

public interface ProductDataHandler {

    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);

    public ProductEntity getProductEntity(String productId);
}
