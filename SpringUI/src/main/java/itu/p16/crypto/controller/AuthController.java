package itu.p16.crypto.controller;

import itu.p16.crypto.service.ApiProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private ApiProxyService apiProxyService;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "auth/inscription";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/verify-url")
    public String showUrlPage() {
        return "auth/urlpage";
    }

    @PostMapping("/register")
    public String handleRegistration(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        // Préparez les données utilisateur
        Map<String, String> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", email);
        userData.put("password", password);

        // Configurez les en-têtes
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(userData, headers);

        // Création d'un RestTemplate avec redirection désactivée
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new org.springframework.http.client.SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setInstanceFollowRedirects(false); // Désactiver le suivi des redirections
            }
        });

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/api/register", requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Message de l'API : " + response.getBody());
                return "redirect:/verify-url";
            } else {
                System.out.println("Erreur de l'API : " + response.getBody());
                return "auth/inscription";
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'appel à l'API Laravel : " + e.getMessage());
            return "auth/inscription";
        }
    }

    @PostMapping("/confirm-url")
    public String handleURLConfirmation(@RequestParam("url") String url, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {

            // Effectuer une requête GET à l'API Laravel
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Si la validation réussit, rediriger vers une page de succès
                return "redirect:/login";
            } else {
                // Si la validation échoue, afficher un message d'erreur
                model.addAttribute("error", "URL validation failed. Please try again.");
                return "auth/urlpage";
            }
        } catch (Exception e) {
            // En cas d'erreur réseau ou autre exception
            model.addAttribute("error", "An error occurred while validating the URL.");
            return "auth/urlpage";
        }
    }


    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        // Préparation des données pour l'API
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        // Configuration des en-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginData, headers);

        // Utilisation de RestTemplate pour appeler l'API Laravel
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:8000/api/login", requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Récupérer le user_id depuis la réponse
                Map<String, Object> responseBody = response.getBody();
                assert responseBody != null;
                Integer userId = (Integer) responseBody.get("user_id");

                // Rediriger avec le user_id comme paramètre
                return "redirect:/confirm-pin?user_id=" + userId;
            }
            else {
            System.out.println("Erreur API : " + response.getBody());
            return "auth/login"; // Retourner à la page de connexion avec un message d'erreur
            }
        } catch (Exception e) {
            // Gestion des exceptions
            System.out.println("Erreur lors de l'appel à l'API Laravel : " + e.getMessage());
            return "auth/login"; // Retourne à la page de connexion avec un message d'erreur
        }
    }

    @GetMapping("/confirm-pin")
    public String showPinConfirmationPage(@RequestParam("user_id") Integer userId, Model model) {
        model.addAttribute("user_id", userId);
        return "auth/confirm-pin"; // Vue JSP pour confirmer le PIN
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "page/welcome"; // Vue JSP pour confirmer le PIN
    }

    @PostMapping("/confirm-pin")
    public String handlePinConfirmation(
            @RequestParam("pin1") String pin1,
            @RequestParam("pin2") String pin2,
            @RequestParam("pin3") String pin3,
            @RequestParam("pin4") String pin4,
            @RequestParam("pin5") String pin5,
            @RequestParam("pin6") String pin6,
            @RequestParam("user_id") Integer userId
    ) {
        // Construire le code PIN complet
        String fullPin = pin1 + pin2 + pin3 + pin4 + pin5 + pin6;

        System.out.println("PIN saisi : " + fullPin);

        // Vous pouvez ici appeler une API Laravel pour vérifier le PIN, par exemple :
        Map<String, String> pinData = new HashMap<>();
        pinData.put("PIN", fullPin);
        pinData.put("user_id", userId.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Forcer Laravel à répondre en JSON

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(pinData, headers);


        RestTemplate restTemplate = new RestTemplate();

        try {
            // Appel à l'API Laravel pour vérifier le PIN
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/api/verify-mfa", requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/dashboard"; // Redirige vers le tableau de bord après validation réussie
            } else {
                System.out.println("Erreur de confirmation du PIN : " + response.getBody());
                return "auth/confirm-pin"; // Retourner à la page PIN avec un message d'erreur
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la confirmation du PIN : " + e.getMessage());
            return "auth/confirm-pin";
        }
    }

}
