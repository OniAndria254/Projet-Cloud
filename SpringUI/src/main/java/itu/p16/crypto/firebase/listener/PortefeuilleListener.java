package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.portefeuille.PortefeuilleSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PortefeuilleListener {

    private final PortefeuilleSyncService portefeuilleSyncService;

    @Autowired
    public PortefeuilleListener(@Lazy PortefeuilleSyncService portefeuilleSyncService) {
        this.portefeuilleSyncService = portefeuilleSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(Portefeuille portefeuille) {
        if (!portefeuille.isSyncFromFirestore()) {
            portefeuilleSyncService.saveAsDocument(portefeuille);
        }
        portefeuille.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }

    @PostUpdate
    @Transactional
    public void apresModification(Portefeuille portefeuille) {
        if (!portefeuille.isSyncFromFirestore()) {
            portefeuilleSyncService.updateAsDocument(portefeuille);
        }
        portefeuille.setSyncFromFirestore(false); // Réinitialisation après synchronisation
    }
}
