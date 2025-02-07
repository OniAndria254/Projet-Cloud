package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.transactioncrypto.TransactionCryptoSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionCryptoListener {

    private final TransactionCryptoSyncService transactionCryptoSyncService;

    @Autowired
    public TransactionCryptoListener(@Lazy TransactionCryptoSyncService transactionCryptoSyncService) {
        this.transactionCryptoSyncService = transactionCryptoSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(TransactionCrypto transactionCrypto) {
        if (!transactionCrypto.isSyncFromFirestore()) {
            transactionCryptoSyncService.saveAsDocument(transactionCrypto);
        }
        transactionCrypto.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }

    @PostUpdate
    @Transactional
    public void apresModification(TransactionCrypto transactionCrypto) {
        if (!transactionCrypto.isSyncFromFirestore()) {
            transactionCryptoSyncService.updateAsDocument(transactionCrypto);
        }
        transactionCrypto.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }
}
