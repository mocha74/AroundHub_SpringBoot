package studio.thinkground.aroundhub.service.impl;

import java.net.URI;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import studio.thinkground.aroundhub.controller.ShortUrlController;
import studio.thinkground.aroundhub.data.dao.ShortUrlDAO;
import studio.thinkground.aroundhub.data.dto.NaverUriDto;
import studio.thinkground.aroundhub.data.dto.ShortUrlResponseDto;
import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;
import studio.thinkground.aroundhub.service.ShortUrlService;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    ShortUrlDAO shortUrlDAO;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO) {
        this.shortUrlDAO = shortUrlDAO;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[getShortUrl] Response DTO : {}", originalUrl);
        ShortUrlEntity getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if(getShortUrlEntity == null) {
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
            String hash = responseEntity.getBody().getResult().getHash();

            ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
            shortUrlEntity.setOrgUrl(orgUrl);
            shortUrlEntity.setUrl(shortUrl);
            shortUrlEntity.setHash(hash);

            shortUrlDAO.saveShortUrl(shortUrlEntity);
        } else {
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
        return shortUrlResponseDto;

    }

    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);
        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);
        LOGGER.info("generateShortUrl Response DTO : {}", shortUrlResponseDto.toString());

        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if(url.contains("me2.do")) {
            LOGGER.info("[deleteShortUrl] Reqeust Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        } else {
            LOGGER.info("[deleteShortUrl] Request Url is 'Original Url'.");
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }


    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret, String originalUrl) {
        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/util/shorturl")
            .queryParam("url", originalUrl)
            .encode()
            .build()
            .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity("", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, NaverUriDto.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete");

        return responseEntity;

    }
}
