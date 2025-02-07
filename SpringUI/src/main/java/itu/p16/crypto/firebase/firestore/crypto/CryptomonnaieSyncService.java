package itu.p16.crypto.firebase.firestore.crypto;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.CryptomonnaieService;
import org.springframework.stereotype.Service;

@Service
public class CryptomonnaieSyncService extends GenericSyncService<Cryptomonnaie, CryptomonnaieDocument> {

    public CryptomonnaieSyncService(Firestore firestore, CryptomonnaieService cryptomonnaieService) {
        super(firestore, cryptomonnaieService, "cryptomonnaie");
    }

    @Override
    protected CryptomonnaieDocument toDocument(Cryptomonnaie entity) {
        return new CryptomonnaieDocument(entity);
    }

    @Override
    protected Cryptomonnaie toEntity(CryptomonnaieDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(Cryptomonnaie entity) {
        return entity.getIdCryptomonnaie().toString();
    }

    @Override
    protected Class<CryptomonnaieDocument> getDocumentClass() {
        return CryptomonnaieDocument.class;
    }
}
