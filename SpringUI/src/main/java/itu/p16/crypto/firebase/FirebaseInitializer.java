package itu.p16.crypto.firebase;

import itu.p16.crypto.firebase.auth.UsersSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class FirebaseInitializer {
//
//    private final CoursSyncService coursSyncService;
//    private final PurchaseSyncService purchaseSyncService;
//    private final CryptoFavSyncService cryptoFavSyncService;
    private final UsersSyncService service;

    @PostConstruct
    public void init() throws Exception {
        System.out.println("Initializing Firebase");

//        service.syncWithFirebase();
        // Appeler la méthode de synchronisation
//        coursSyncService.syncWithFirebase();

//        purchaseSyncService.syncWithFirebase();

//        cryptoFavSyncService.syncWithFirebase();

    }
}
