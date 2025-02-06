package itu.p16.crypto.firebase.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import itu.p16.crypto.firebase.auth.UsersSyncService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirestoreConfiguration {

    @Value("classpath:private-key.json") // <-- Supprime le "/" avant private-key.json
    private Resource privateKey;

    private final UsersSyncService service;

    public FirestoreConfiguration(UsersSyncService service) {
        this.service = service;
    }

    @Bean
    public Firestore firestore() throws IOException {
        if (!privateKey.exists()) {
            throw new IOException("Le fichier private-key.json est introuvable !");
        }

        try (InputStream credentials = privateKey.getInputStream()) {
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(credentials))
                    .build();

            // Vérifie si Firebase est déjà initialisé
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(firebaseOptions);
                System.out.println("Firebase initialisé !");
            } else {
                System.out.println("Firebase déjà initialisé.");
            }

            service.syncWithFirebase();
            return FirestoreClient.getFirestore();
        }
    }
}
