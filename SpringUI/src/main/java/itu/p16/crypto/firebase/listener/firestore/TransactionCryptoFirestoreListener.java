package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.transactioncrypto.TransactionCryptoDocument;
import itu.p16.crypto.repository.TransactionCryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCryptoFirestoreListener {

    private final Firestore firestore;
    private final TransactionCryptoRepository transactionCryptoRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("transaction_crypto")
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
        TransactionCryptoDocument document = documentSnapshot.toObject(TransactionCryptoDocument.class);
        TransactionCrypto transactionCrypto = convertToEntity(document);

        // Empêcher la boucle infinie Firestore ↔ PostgreSQL
        if (!transactionCrypto.isSyncFromFirestore()) {
            transactionCrypto.setSyncFromFirestore(true);
            transactionCryptoRepository.save(transactionCrypto);
        }
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        TransactionCryptoDocument document = documentSnapshot.toObject(TransactionCryptoDocument.class);
        TransactionCrypto transactionCrypto = convertToEntity(document);

        Optional<TransactionCrypto> existingTransaction = transactionCryptoRepository.findById(transactionCrypto.getIdTransactionCrypto());
        if (existingTransaction.isPresent()) {
            TransactionCrypto existing = existingTransaction.get();

            // Vérifier si les données ont changé avant mise à jour
            if (!existing.equals(transactionCrypto) && !transactionCrypto.isSyncFromFirestore()) {
                transactionCrypto.setSyncFromFirestore(true);
                transactionCryptoRepository.save(transactionCrypto);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idTransactionCrypto = documentSnapshot.getId();
        transactionCryptoRepository.deleteById(Integer.valueOf(idTransactionCrypto));
    }

    // Convertir le document Firestore en entité JPA
    private TransactionCrypto convertToEntity(TransactionCryptoDocument document) {
        return document.toEntity();
    }
}
