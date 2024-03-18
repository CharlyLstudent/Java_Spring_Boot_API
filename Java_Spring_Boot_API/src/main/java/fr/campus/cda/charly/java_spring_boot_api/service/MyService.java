package fr.campus.cda.charly.java_spring_boot_api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {
    private final RestTemplate restTemplate;

    private final String baseUrl = "http://172.22.114.56:9191";

    public MyService(){
        this.restTemplate = new RestTemplate();
    }

    public String callEndpoint(){
        ResponseEntity<String> result = restTemplate.getForEntity(baseUrl + "/Matches", String.class);
        return result.getBody();
    }
}
