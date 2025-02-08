package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.*;
import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.firebase.firestore.fav.CryptoFavDocument;
import itu.p16.crypto.repository.CryptoFavRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoFavFirestoreListener {

    private final Firestore firestore;
    private final CryptoFavRepository cryptoFavRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("crypto_fav")
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
        CryptoFavDocument document = documentSnapshot.toObject(CryptoFavDocument.class);
        CryptoFav CryptoFav = convertToEntity(document);

        // Marquer comme synchronisé pour éviter le renvoi à Firestore
        CryptoFav.setSyncFromFirestore(true);
        cryptoFavRepository.save(CryptoFav);
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        CryptoFavDocument document = documentSnapshot.toObject(CryptoFavDocument.class);
        CryptoFav cryptoFav = convertToEntity(document);

        Optional<CryptoFav> existingCryptoFav = cryptoFavRepository.findById(cryptoFav.getId());
        if (existingCryptoFav.isPresent()) {
            CryptoFav existing = existingCryptoFav.get();

            // Vérifier si les données ont changé pour éviter les mises à jour inutiles
            if (!existing.equals(cryptoFav)) {
                cryptoFav.setSyncFromFirestore(true);
                cryptoFavRepository.save(cryptoFav);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idCryptoFav = documentSnapshot.getId();
        cryptoFavRepository.deleteById(Integer.valueOf(idCryptoFav));
    }

    // Convertir le document Firestore en entité JPA
    private CryptoFav convertToEntity(CryptoFavDocument document) {
        return document.toEntity();
    }
}
