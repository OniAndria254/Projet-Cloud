package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.*;
import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.firebase.firestore.historique.HistoriqueCoursDocument;
import itu.p16.crypto.repository.HistoriqueCoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoriqueCoursFirestoreListener {

    private final Firestore firestore;
    private final HistoriqueCoursRepository historiqueCoursRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("historique_cours")
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.out.println("Listen failed: " + e);
                        return;
                    }
                    for (DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
                        switch (documentChange.getType()) {
                            case ADDED:
                                handleAddedDocument(documentChange.getDocument());
                                break;
                            case MODIFIED:
                                handleModifiedDocument(documentChange.getDocument());
                                break;
                            case REMOVED:
                                handleRemovedDocument(documentChange.getDocument());
                                break;
                        }
                    }
                });
    }

    // Gérer l'ajout d'un document
    private void handleAddedDocument(DocumentSnapshot documentSnapshot) {
        HistoriqueCoursDocument document = documentSnapshot.toObject(HistoriqueCoursDocument.class);
        HistoriqueCours historiqueCours = convertToEntity(document);

        // Marquer comme synchronisé pour éviter le renvoi à Firestore
        historiqueCours.setSyncFromFirestore(true);
        historiqueCoursRepository.save(historiqueCours);
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        HistoriqueCoursDocument document = documentSnapshot.toObject(HistoriqueCoursDocument.class);
        HistoriqueCours historiqueCours = convertToEntity(document);

        Optional<HistoriqueCours> existingHistoriqueCours = historiqueCoursRepository.findById(historiqueCours.getIdHistoriqueCours());
        if (existingHistoriqueCours.isPresent()) {
            HistoriqueCours existing = existingHistoriqueCours.get();

            // Vérifier si les données ont changé pour éviter les mises à jour inutiles
            if (!existing.equals(historiqueCours)) {
                historiqueCours.setSyncFromFirestore(true);
                historiqueCoursRepository.save(historiqueCours);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idHistoriqueCours = documentSnapshot.getId();
        historiqueCoursRepository.deleteById(Integer.valueOf(idHistoriqueCours));
    }

    // Convertir le document Firestore en entité JPA
    private HistoriqueCours convertToEntity(HistoriqueCoursDocument document) {
        return document.toEntity();
    }
}
