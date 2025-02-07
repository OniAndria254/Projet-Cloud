package itu.p16.crypto.firebase.firestore.historique;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.HistoriqueCoursService;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueCoursSyncService extends GenericSyncService<HistoriqueCours, HistoriqueCoursDocument> {

    public HistoriqueCoursSyncService(Firestore firestore, HistoriqueCoursService historiqueCoursService) {
        super(firestore, historiqueCoursService, "historique_cours");
    }

    @Override
    protected HistoriqueCoursDocument toDocument(HistoriqueCours entity) {
        return new HistoriqueCoursDocument(entity);
    }

    @Override
    protected HistoriqueCours toEntity(HistoriqueCoursDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(HistoriqueCours entity) {
        return entity.getIdHistoriqueCours().toString();
    }

    @Override
    protected Class<HistoriqueCoursDocument> getDocumentClass() {
        return HistoriqueCoursDocument.class;
    }
}
