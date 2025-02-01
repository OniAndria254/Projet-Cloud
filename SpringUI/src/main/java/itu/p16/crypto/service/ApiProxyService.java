package itu.p16.crypto.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiProxyService {

    public ResponseEntity<String> registerUser(Map<String, Object> userData) {
        String apiUrl = "http://localhost:8000/api/register";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userData, headers);
        return restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
    }
}
