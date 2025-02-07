package itu.p16.crypto.service;

import itu.p16.crypto.entity.Users;
import itu.p16.crypto.exception.NoUserLoggedException;
import itu.p16.crypto.firebase.firestore.users.UsersSyncService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final HttpSession httpSession;
    private final RestTemplate restTemplate;

    @Value("${laravel.api.url}")
    private String laravelApiUrl;


    public AuthService(RestTemplate restTemplate, HttpSession httpSession) {
        this.restTemplate = restTemplate;
        this.httpSession = httpSession;
    }


    public boolean registerUser(String username, String email, String password) {
        // Prépare les données utilisateur
        Map<String, String> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", email);
        userData.put("password", password);

        // Configure les headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(userData, headers);

        // Désactiver le suivi des redirections
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setInstanceFollowRedirects(false);
            }
        });

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(laravelApiUrl + "/api/register", requestEntity, String.class);
            return response.getStatusCode().is2xxSuccessful(); // Retourne true si l'inscription est réussie
        } catch (Exception e) {
            System.out.println("Erreur lors de l'appel à l'API Laravel : " + e.getMessage());
            return false;
        }
    }
    public Users requireUser() throws NoUserLoggedException {
        Object obj = httpSession.getAttribute("user");
        if(obj instanceof Users u) {
            return u;
        }
        throw new NoUserLoggedException();
    }


}
