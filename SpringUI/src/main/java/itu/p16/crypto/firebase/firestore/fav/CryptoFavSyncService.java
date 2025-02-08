package itu.p16.crypto.firebase.firestore.fav;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.CryptoFavService;
import org.springframework.stereotype.Service;

@Service
public class CryptoFavSyncService extends GenericSyncService<CryptoFav, CryptoFavDocument> {

    public CryptoFavSyncService(Firestore firestore, CryptoFavService cryptoFavService) {
        super(firestore, cryptoFavService, "crypto_fav");
    }

    @Override
    protected CryptoFavDocument toDocument(CryptoFav entity) {
        return new CryptoFavDocument(entity);
    }

    @Override
    protected CryptoFav toEntity(CryptoFavDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(CryptoFav entity) {
        return entity.getId().toString();
    }

    @Override
    protected Class<CryptoFavDocument> getDocumentClass() {
        return CryptoFavDocument.class;
    }
}
