package itu.p16.crypto.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private HttpSession session;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private final AuthService authService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "auth/inscription";
    }

    @PostMapping("/register")
    public String handleRegistration(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
        try {
            boolean success = authService.registerUser(username, email, password);

            if (success) {
                return "redirect:/verify-url"; // Rediriger après inscription réussie
            } else {
                model.addAttribute("error", "Erreur lors de l'inscription.");
                return "auth/inscription"; // Retourner la page d'inscription avec un message d'erreur
            }
        } catch (Exception e) {
            model.addAttribute("error", "Échec de la connexion au service Laravel.");
            return "auth/inscription";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/verify-url")
    public String showUrlPage() {
        return "auth/urlpage";
    }

    @PostMapping("/confirm-url")
    public String handleURLConfirmation(
            @RequestParam("url") String url,
            Model model,
            HttpSession session // Ajout de la session
    ) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);

        try {
            // Effectuer une requête GET à l'API Laravel
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            System.out.println(response.toString());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                System.out.println(responseBody.toString());

                if (responseBody.containsKey("user")) {
                    // Stocker l'utilisateur en session
                    session.setAttribute("user", responseBody.get("user"));
                    System.out.println("Utilisateur stocké en session : " + responseBody.get("user"));
                }

                return "redirect:/dashboard"; // Redirection après validation réussie
            } else {
                // Si la validation échoue, afficher un message d'erreur
                model.addAttribute("error", "URL validation failed. Please try again.");
                System.out.println();
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

    /*@PostMapping("/loginAdmin")
    public String loginAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginData, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(laravelApiUrl + "/api/loginAdmin", requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody.containsKey("user")) {
                    Map<String, Object> userMap = (Map<String, Object>) responseBody.get("user");
                    Users user = Users.fromMap(userMap);
                    System.out.println(user.getIdRole());
                    session.setAttribute("user", user);
                    System.out.println("Admin connecté : " + user);
                }
                return "redirect:/transaction/buy-sell";
            } else {
                model.addAttribute("error", "Échec de la connexion administrateur.");
                return "auth/admin-login";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", "Erreur lors de la connexion : " + e.getMessage());
            return "auth/admin-login";
        }
    }*/

    @PostMapping("/loginAdmin")
    public String loginAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginData, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(laravelApiUrl + "/api/loginAdmin", requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody.containsKey("user")) {
                    Map<String, Object> userMap = (Map<String, Object>) responseBody.get("user");
                    Users user = Users.fromMap(userMap);
                    System.out.println("Role de l'utilisateur : " + user.getIdRole());
                    // Stocker l'utilisateur dans la session
                    session.setAttribute("user", user);
                    // Indiquer dans la session que l'utilisateur est administrateur
                    session.setAttribute("isAdmin", true);
                    System.out.println("Admin connecté : " + user);
                }
                // Redirigez vers la page protégée sans utiliser de paramètre dans l'URL
                return "redirect:/transaction/buy-sell";
            } else {
                model.addAttribute("error", "Échec de la connexion administrateur.");
                return "auth/admin-login";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", "Erreur lors de la connexion : " + e.getMessage());
            return "auth/admin-login";
        }
    }


    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Object userObj = session.getAttribute("user");

        if (userObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Aucun utilisateur en session");
        }

        if (userObj instanceof Users) {
            return ResponseEntity.ok().body(userObj);
        }

        // Si l'objet est un LinkedHashMap, le convertir en Users
        try {
            Users user = objectMapper.convertValue(userObj, Users.class);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la conversion de l'utilisateur : " + e.getMessage());
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
            @RequestParam("user_id") Integer userId,
            HttpSession session // Ajout de la session
    ) {
        // Construire le code PIN complet
        String fullPin = pin1 + pin2 + pin3 + pin4 + pin5 + pin6;
        System.out.println("PIN saisi : " + fullPin);

        // Construire les données pour l'API Laravel
        Map<String, String> pinData = new HashMap<>();
        pinData.put("PIN", fullPin);
        pinData.put("user_id", userId.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(pinData, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Appel à l'API Laravel pour vérifier le PIN
            ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:8000/api/verify-mfa", requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();

                if (responseBody.containsKey("user")) {
                    // Stocker l'utilisateur en session
                    session.setAttribute("user", responseBody.get("user"));
                    System.out.println("Utilisateur stocké en session : " + responseBody.get("user"));
                }

                return "redirect:/dashboard"; // Rediriger après validation réussie
            } else {
                System.out.println("Erreur de confirmation du PIN : " + response.getBody());
                return "auth/confirm-pin"; // Retourner à la page de confirmation avec une erreur
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la confirmation du PIN : " + e.getMessage());
            return "auth/confirm-pin";
        }
    }


}
