package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.PortefeuilleCrypto;
import itu.p16.crypto.firebase.firestore.portefeuille.PortefeuilleCryptoSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PortefeuilleCryptoListener {

    private final PortefeuilleCryptoSyncService portefeuilleCryptoSyncService;

    @Autowired
    public PortefeuilleCryptoListener(@Lazy PortefeuilleCryptoSyncService portefeuilleCryptoSyncService) {
        this.portefeuilleCryptoSyncService = portefeuilleCryptoSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(PortefeuilleCrypto portefeuilleCrypto) {
        if (!portefeuilleCrypto.isSyncFromFirestore()) {
            portefeuilleCryptoSyncService.saveAsDocument(portefeuilleCrypto);
        }
        portefeuilleCrypto.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }

    @PostUpdate
    @Transactional
    public void apresModification(PortefeuilleCrypto portefeuilleCrypto) {
        if (!portefeuilleCrypto.isSyncFromFirestore()) {
            portefeuilleCryptoSyncService.updateAsDocument(portefeuilleCrypto);
        }
        portefeuilleCrypto.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }
}
