package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.firebase.firestore.transaction.TransactionFondsSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionFondsListener {

    private final TransactionFondsSyncService transactionFondsSyncService;

    @Autowired
    public TransactionFondsListener(@Lazy TransactionFondsSyncService transactionFondsSyncService) {
        this.transactionFondsSyncService = transactionFondsSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(TransactionFonds transactionFonds) {
        if (!transactionFonds.isSyncFromFirestore()) {
            transactionFondsSyncService.saveAsDocument(transactionFonds);
        }
        transactionFonds.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }

    @PostUpdate
    @Transactional
    public void apresModification(TransactionFonds transactionFonds) {
        if (!transactionFonds.isSyncFromFirestore()) {
            transactionFondsSyncService.updateAsDocument(transactionFonds);
        }
        transactionFonds.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }
}
