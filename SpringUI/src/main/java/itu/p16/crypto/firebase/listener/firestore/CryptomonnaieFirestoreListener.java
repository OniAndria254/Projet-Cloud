package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.*;
import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.firebase.firestore.crypto.CryptomonnaieDocument;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptomonnaieFirestoreListener {

    private final Firestore firestore;
    private final CryptomonnaieRepository cryptomonnaieRepository;

    @PostConstruct
    public void listenForChanges() {

        firestore.collection("cryptomonnaie")
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
        CryptomonnaieDocument document = documentSnapshot.toObject(CryptomonnaieDocument.class);
        Cryptomonnaie cryptomonnaie = convertToEntity(document);

        // Marquer comme synchronisé pour éviter le renvoi à Firestore
        cryptomonnaie.setSyncFromFirestore(true);
        cryptomonnaieRepository.save(cryptomonnaie);
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        CryptomonnaieDocument document = documentSnapshot.toObject(CryptomonnaieDocument.class);
        Cryptomonnaie cryptomonnaie = convertToEntity(document);
        cryptomonnaie.setSyncFromFirestore(true);
        cryptomonnaieRepository.save(cryptomonnaie);
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idCryptomonnaie = documentSnapshot.getId();
        cryptomonnaieRepository.deleteById(Long.valueOf(idCryptomonnaie));
    }

    // Convertir le document Firestore en entité JPA
    private Cryptomonnaie convertToEntity(CryptomonnaieDocument document) {
        return document.toEntity();
    }
}
