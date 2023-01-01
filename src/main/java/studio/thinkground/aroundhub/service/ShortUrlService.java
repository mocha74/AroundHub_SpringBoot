package studio.thinkground.aroundhub.service;

import org.springframework.stereotype.Service;
import studio.thinkground.aroundhub.data.dto.ShortUrlResponseDto;

@Service
public interface ShortUrlService {

    ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDto deleteByShortUrl(String shortUrl);

    ShortUrlResponseDto deleteByOriginalUrl(String originalUrl);
}
