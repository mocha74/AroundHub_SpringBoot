package studio.thinkground.aroundhub.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.thinkground.aroundhub.data.dao.ShortUrlDAO;
import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;
import studio.thinkground.aroundhub.data.repository.ShortUrlRepository;

@Service
public class ShortUrlDAOImpl implements ShortUrlDAO {

    ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity) {
        return null;
    }
}
