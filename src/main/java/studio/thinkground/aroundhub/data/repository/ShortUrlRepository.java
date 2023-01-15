package studio.thinkground.aroundhub.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import studio.thinkground.aroundhub.data.entity.ProductEntity;
import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {

    ShortUrlEntity findByUrl(String url);

    ShortUrlEntity findByOrgUrl(String originalUrl);

}
