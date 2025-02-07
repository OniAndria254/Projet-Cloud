package itu.p16.crypto.firebase.listener;

import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.users.UsersSyncService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserListener {

    private final UsersSyncService usersSyncService;

    @Autowired
    public UserListener(@Lazy UsersSyncService usersSyncService) {
        this.usersSyncService = usersSyncService;
    }

    @PostPersist
    @Transactional
    public void apresSauvegarde(Users user) {
        if (!user.isSyncFromFirestore()) {
            usersSyncService.saveAsDocument(user);
        }
        user.setSyncFromFirestore(false); // Réinitialiser après l'opération
    }

    @PostUpdate
    @Transactional
    public void apresModification(Users user) {
        if (!user.isSyncFromFirestore()) {
            usersSyncService.updateAsDocument(user);
        }
        user.setSyncFromFirestore(false); // Réinitialiser après l'opération
    }
}
