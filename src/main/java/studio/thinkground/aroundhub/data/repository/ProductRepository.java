package studio.thinkground.aroundhub.data.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import studio.thinkground.aroundhub.data.entity.ProductEntity;

public interface ProductRepository extends JpaRepositoryImplementation<ProductEntity, String> {

}
