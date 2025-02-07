package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.firebase.firestore.historique.HistoriqueCoursSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HistoriqueCoursListener {

    private final HistoriqueCoursSyncService historiqueCoursSyncService;

    @Autowired
    public HistoriqueCoursListener(@Lazy HistoriqueCoursSyncService historiqueCoursSyncService) {
        this.historiqueCoursSyncService = historiqueCoursSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(HistoriqueCours historiqueCours) {
        if (!historiqueCours.isSyncFromFirestore()) {
            historiqueCoursSyncService.saveAsDocument(historiqueCours);
        }
        historiqueCours.setSyncFromFirestore(false); // Réinitialisation après synchro
    }

    @PostUpdate
    @Transactional
    public void apresModification(HistoriqueCours historiqueCours) {
        if (!historiqueCours.isSyncFromFirestore()) {
            historiqueCoursSyncService.updateAsDocument(historiqueCours);
        }
        historiqueCours.setSyncFromFirestore(false); // Réinitialisation après synchro
    }
}
