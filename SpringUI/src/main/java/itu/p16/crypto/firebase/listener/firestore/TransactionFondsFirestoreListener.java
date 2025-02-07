package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.firebase.firestore.transaction.TransactionFondsDocument;
import itu.p16.crypto.repository.TransactionFondsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionFondsFirestoreListener {

    private final Firestore firestore;
    private final TransactionFondsRepository transactionFondsRepository;

    @PostConstruct
    public void listenForChanges() {
        firestore.collection("transaction_Fonds")
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
        TransactionFondsDocument document = documentSnapshot.toObject(TransactionFondsDocument.class);
        TransactionFonds transactionFonds = convertToEntity(document);

        // Empêcher la boucle infinie Firestore ↔ PostgreSQL
        if (!transactionFonds.isSyncFromFirestore()) {
            transactionFonds.setSyncFromFirestore(true);
            transactionFondsRepository.save(transactionFonds);
        }
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        TransactionFondsDocument document = documentSnapshot.toObject(TransactionFondsDocument.class);
        TransactionFonds transactionFonds = convertToEntity(document);

        Optional<TransactionFonds> existingTransaction = transactionFondsRepository.findById(transactionFonds.getIdTransactionFonds());
        if (existingTransaction.isPresent()) {
            TransactionFonds existing = existingTransaction.get();

            // Vérifier si les données ont changé avant mise à jour
            if (!existing.equals(transactionFonds) && !transactionFonds.isSyncFromFirestore()) {
                transactionFonds.setSyncFromFirestore(true);
                transactionFondsRepository.save(transactionFonds);
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String idTransactionFonds = documentSnapshot.getId();
        transactionFondsRepository.deleteById(Integer.valueOf(idTransactionFonds));
    }

    // Convertir le document Firestore en entité JPA
    private TransactionFonds convertToEntity(TransactionFondsDocument document) {
        return document.toEntity();
    }
}
