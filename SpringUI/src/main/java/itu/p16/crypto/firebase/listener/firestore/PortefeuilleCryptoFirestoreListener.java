package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.*;
import itu.p16.crypto.entity.PortefeuilleCrypto;
import itu.p16.crypto.firebase.firestore.portefeuille.PortefeuilleCryptoDocument;
import itu.p16.crypto.repository.PortefeuilleCryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortefeuilleCryptoFirestoreListener {

    private final Firestore firestore;
    private final PortefeuilleCryptoRepository portefeuilleCryptoRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("portefeuille_crypto")
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
        PortefeuilleCryptoDocument document = documentSnapshot.toObject(PortefeuilleCryptoDocument.class);
        PortefeuilleCrypto portefeuilleCrypto = convertToEntity(document);

        // Marquer comme synchronisé pour éviter le renvoi à Firestore
        portefeuilleCrypto.setSyncFromFirestore(true);
        portefeuilleCryptoRepository.save(portefeuilleCrypto);
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        PortefeuilleCryptoDocument document = documentSnapshot.toObject(PortefeuilleCryptoDocument.class);
        PortefeuilleCrypto portefeuilleCrypto = convertToEntity(document);

        Optional<PortefeuilleCrypto> existingPortefeuille = portefeuilleCryptoRepository.findById(portefeuilleCrypto.getIdPortefeuilleCrypto());
        if (existingPortefeuille.isPresent()) {
            PortefeuilleCrypto existing = existingPortefeuille.get();

            // Vérifier si les données ont changé pour éviter les mises à jour inutiles
            if (!existing.equals(portefeuilleCrypto)) {
                portefeuilleCrypto.setSyncFromFirestore(true);
                portefeuilleCryptoRepository.save(portefeuilleCrypto);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idPortefeuilleCrypto = documentSnapshot.getId();
        portefeuilleCryptoRepository.deleteById(Integer.valueOf(idPortefeuilleCrypto));
    }

    // Convertir le document Firestore en entité JPA
    private PortefeuilleCrypto convertToEntity(PortefeuilleCryptoDocument document) {
        return document.toEntity();
    }
}
