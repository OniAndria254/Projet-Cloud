package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.*;
import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.portefeuille.PortefeuilleDocument;
import itu.p16.crypto.repository.PortefeuilleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortefeuilleFirestoreListener {

    private final Firestore firestore;
    private final PortefeuilleRepository portefeuilleRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("portefeuille")
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
        PortefeuilleDocument document = documentSnapshot.toObject(PortefeuilleDocument.class);
        Portefeuille portefeuille = convertToEntity(document);

        // Marquer comme synchronisé pour éviter le renvoi à Firestore
        portefeuille.setSyncFromFirestore(true);
        portefeuilleRepository.save(portefeuille);
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        PortefeuilleDocument document = documentSnapshot.toObject(PortefeuilleDocument.class);
        Portefeuille portefeuille = convertToEntity(document);

        Optional<Portefeuille> existingPortefeuille = portefeuilleRepository.findById(portefeuille.getIdPortefeuille());
        if (existingPortefeuille.isPresent()) {
            Portefeuille existing = existingPortefeuille.get();

            // Vérifier si les données ont changé pour éviter les mises à jour inutiles
            if (!existing.equals(portefeuille)) {
                portefeuille.setSyncFromFirestore(true);
                portefeuilleRepository.save(portefeuille);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idPortefeuille = documentSnapshot.getId();
        portefeuilleRepository.deleteById(Integer.valueOf(idPortefeuille));
    }

    // Convertir le document Firestore en entité JPA
    private Portefeuille convertToEntity(PortefeuilleDocument document) {
        return document.toEntity();
    }
}
