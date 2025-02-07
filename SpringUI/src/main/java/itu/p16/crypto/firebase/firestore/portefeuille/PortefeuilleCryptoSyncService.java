package itu.p16.crypto.firebase.firestore.portefeuille;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.entity.PortefeuilleCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.PortefeuilleCryptoService;
import itu.p16.crypto.service.PortefeuilleService;
import org.springframework.stereotype.Service;

@Service
public class PortefeuilleCryptoSyncService extends GenericSyncService<PortefeuilleCrypto, PortefeuilleCryptoDocument> {

    public PortefeuilleCryptoSyncService(Firestore firestore, PortefeuilleCryptoService portefeuilleCryptoService) {
        super(firestore, portefeuilleCryptoService, "portefeuille_crypto");
    }

    @Override
    protected PortefeuilleCryptoDocument toDocument(PortefeuilleCrypto entity) {
        return new PortefeuilleCryptoDocument(entity);
    }

    @Override
    protected PortefeuilleCrypto toEntity(PortefeuilleCryptoDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(PortefeuilleCrypto entity) {
        return null;
    }

    @Override
    protected Class<PortefeuilleCryptoDocument> getDocumentClass() {
        return PortefeuilleCryptoDocument.class;
    }
}
