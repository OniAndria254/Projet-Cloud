package itu.p16.crypto.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersSyncService {

    private final UsersService usersService;

    /**
     * Synchroniser les comptes existants avec Firebase Auth.
     */
    @EventListener(ApplicationReadyEvent.class)
    public String syncWithFirebase() {
        List<Users> users = usersService.findAll();

        int nbSynced = 0, nbTotal = users.size();
        log.info("Début de la synchronisation des comptes avec Firebase Auth ({} comptes à traiter)", nbTotal);

        for (Users user : users) {
            try {
                FirebaseAuth.getInstance().getUserByEmail(user.getEmail());
                log.debug("Utilisateur déjà synchronisé : {}", user.getEmail());
            } catch (Exception e) {
                try {
                    // Warning: on utilise un password car il n'y pas encore de fonction pour fetch le mot de passe depuis Identity API -> Le password fetched doit etre non hache
                    // Info: le password temp est "fufu_is_password"
                    String password = "fufu_is_password";

                    CreateRequest request = new CreateRequest()
                            .setEmail(user.getEmail())
                            .setPassword(user.getPassword()) // Remplace par un mot de passe temporaire si haché
                            .setDisplayName(user.getUsername());

                    // Warning: Envoyer un email de reinitialisation de password pour changer le password temp
                    FirebaseAuth.getInstance().generatePasswordResetLink(user.getEmail());

                    UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
                    nbSynced++;
                    log.info("Utilisateur ajouté à Firebase : {} (UID: {})", user.getEmail(), userRecord.getUid());
                } catch (Exception ex) {
                    log.error("Erreur lors de l'ajout de l'utilisateur {} à Firebase : {}",user.getEmail(), ex.getMessage());
                }
            }
        }

        log.info("Synchronisation terminée : {} comptes synchronisés sur {} au total", nbSynced, nbTotal);
        return String.format("Synchronisation terminée : %d comptes synchronisés sur %d au total", nbSynced, nbTotal);
    }
}
