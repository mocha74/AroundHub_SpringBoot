package studio.thinkground.aroundhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.thinkground.aroundhub.data.dto.ProductDto;
import studio.thinkground.aroundhub.dto.MemberDTO;
import studio.thinkground.aroundhub.service.RestTemplateService;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {

    RestTemplateService restTemplateService;

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    // http://localhost:8080/api/v1/restTemplate/around-hub
    @GetMapping(value = "/around-hub")
    public String getAroundHub() {
        return restTemplateService.getAroundHub();
    }

    // http://localhost:8080/api/v1/restTemplate/name
    @GetMapping(value = "/name")
    public String getName() {
        return restTemplateService.getName();
    }

    // http://localhost:8080/api/v1/restTemplate/name2
    @GetMapping(value = "/name2")
    public String getName2() {
        return restTemplateService.getName2();
    }

    // http://localhost:8080/api/v1/restTemplate/dto
    @GetMapping(value = "/dto")
    public ResponseEntity<MemberDTO> postDto() {
        return restTemplateService.postDto();
    }

    // http://localhost:8080/api/v1/restTemplate/add-header
    @GetMapping(value = "/add-header")
    public ResponseEntity<MemberDTO> addHeader() {
        return restTemplateService.addHeader();
    }
}
