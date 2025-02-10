package itu.p16.crypto.firebase.firestore.portefeuille;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.PortefeuilleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PortefeuilleSyncService extends GenericSyncService<Portefeuille, PortefeuilleDocument> {

    public PortefeuilleSyncService(Firestore firestore, @Lazy PortefeuilleService portefeuilleService) {
        super(firestore, portefeuilleService, "portefeuille");
    }

    @Override
    protected PortefeuilleDocument toDocument(Portefeuille entity) {
        return new PortefeuilleDocument(entity);
    }

    @Override
    protected Portefeuille toEntity(PortefeuilleDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(Portefeuille entity) {
        return entity.getIdPortefeuille().toString();
    }

    @Override
    protected Class<PortefeuilleDocument> getDocumentClass() {
        return PortefeuilleDocument.class;
    }
}
