package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.firebase.firestore.fav.CryptoFavSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CryptoFavListener {

    private final CryptoFavSyncService cryptoFavSyncService;

    @Autowired
    public CryptoFavListener(@Lazy CryptoFavSyncService cryptoFavSyncService) {
        this.cryptoFavSyncService = cryptoFavSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(CryptoFav cryptoFav) {
        if (!cryptoFav.isSyncFromFirestore()) {
            cryptoFavSyncService.saveAsDocument(cryptoFav);
        }
        cryptoFav.setSyncFromFirestore(false); // Réinitialisation après synchro
    }

    @PostUpdate
    @Transactional
    public void apresModification(CryptoFav cryptoFav) {
        if (!cryptoFav.isSyncFromFirestore()) {
            cryptoFavSyncService.updateAsDocument(cryptoFav);
        }
        cryptoFav.setSyncFromFirestore(false); // Réinitialisation après synchro
    }
}
