package studio.thinkground.aroundhub.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import studio.thinkground.aroundhub.entity.ProductEntity;

public interface ProductRepository extends JpaRepositoryImplementation<ProductEntity, String> {

}
