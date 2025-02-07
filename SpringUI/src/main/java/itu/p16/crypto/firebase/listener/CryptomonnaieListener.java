package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.firebase.firestore.crypto.CryptomonnaieSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CryptomonnaieListener {

    private final CryptomonnaieSyncService cryptomonnaieSyncService;

    @Autowired
    public CryptomonnaieListener(@Lazy CryptomonnaieSyncService cryptomonnaieSyncService) {
        this.cryptomonnaieSyncService = cryptomonnaieSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(Cryptomonnaie cryptomonnaie) {
        if (!cryptomonnaie.isSyncFromFirestore()) {
            cryptomonnaieSyncService.saveAsDocument(cryptomonnaie);
        }
        cryptomonnaie.setSyncFromFirestore(false); // Réinitialisation après synchro
    }

    @PostUpdate
    @Transactional
    public void apresModification(Cryptomonnaie cryptomonnaie) {
        if (!cryptomonnaie.isSyncFromFirestore()) {
            cryptomonnaieSyncService.updateAsDocument(cryptomonnaie);
        }
        cryptomonnaie.setSyncFromFirestore(false); // Réinitialisation après synchro
    }
}
