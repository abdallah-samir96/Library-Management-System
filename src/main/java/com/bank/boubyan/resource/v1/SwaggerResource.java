package com.bank.boubyan.resource.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/docs")
public class SwaggerResource {
    @Value("${swagger.url}")
    private String SWAGGER_DOCS_URL;
    private final RestTemplate restTemplate;
    public SwaggerResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/generate")
    ResponseEntity<byte[]> generate(){
        byte[] fileContent = restTemplate.getForObject(SWAGGER_DOCS_URL, byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "swaggerFile.json");
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
