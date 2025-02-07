package itu.p16.crypto.firebase;

import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.entity.Statut;
import itu.p16.crypto.entity.TypeTransaction;
import itu.p16.crypto.firebase.firestore.crypto.CryptomonnaieSyncService;
import itu.p16.crypto.firebase.firestore.historique.HistoriqueCoursSyncService;
import itu.p16.crypto.firebase.firestore.portefeuille.PortefeuilleSyncService;
import itu.p16.crypto.firebase.firestore.transaction.TransactionFondsSyncService;
import itu.p16.crypto.firebase.firestore.transactioncrypto.TransactionCryptoSyncService;
import itu.p16.crypto.firebase.firestore.users.UsersSyncService;
import itu.p16.crypto.repository.HistoriqueCoursRepository;
import itu.p16.crypto.repository.StatutRepository;
import itu.p16.crypto.repository.TransactionFondsRepository;
import itu.p16.crypto.repository.TypeTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirebaseInitializer {

    private final UsersSyncService usersSyncService;
    private final CryptomonnaieSyncService cryptomonnaieSyncService;
    private final PortefeuilleSyncService portefeuilleSyncService;
    private final TransactionCryptoSyncService transactionCryptoSyncService;

    private final HistoriqueCoursSyncService historiqueCoursSyncService;
    private final TransactionFondsSyncService transactionFondsSyncService;

    private final HistoriqueCoursRepository historiqueCoursRepository;
    private final TransactionFondsRepository transactionFondsRepository;
    private final StatutRepository statutRepository;
    private final TypeTransactionRepository typeTransactionRepository;

    @PostConstruct
    public void init() {
        log.info("Starting Firebase entity initialization...");

//        historiqueCoursSyncService.syncWithFirebase();
        usersSyncService.syncWithFirebase();
        cryptomonnaieSyncService.syncWithFirebase();
        portefeuilleSyncService.syncWithFirebase();
        transactionCryptoSyncService.syncWithFirebase();

        transactionFondsSyncService.syncWithFirebase();
//

        log.info("Firebase entity tests complete.");
    }

    private void testHistoriqueCours() {

        HistoriqueCours entity = new HistoriqueCours();
        entity.setPrix(new BigDecimal("100.50"));
        entity.setDateEnregistrement(LocalDate.now());
        entity.setIdCryptomonnaie(1);

        HistoriqueCours savedEntity = historiqueCoursRepository.save(entity);

        log.info("Saved HistoriqueCours: " + savedEntity);

        savedEntity.setPrix(new BigDecimal("150.75"));
        historiqueCoursRepository.save(savedEntity);

        HistoriqueCours retrievedEntity = historiqueCoursRepository.findById(savedEntity.getIdHistoriqueCours()).orElse(null);
        log.info("Updated HistoriqueCours: " + retrievedEntity);
    }

    private void testTransactionFonds() {

        Statut statut = statutRepository.findAll().get(0);
        TypeTransaction typeTransaction = typeTransactionRepository.findAll().get(0);

        TransactionFonds entity = new TransactionFonds();
        entity.setIdUtilisateur(1);
        entity.setMontant(new BigDecimal("500.00"));
        entity.setDateTransaction(java.sql.Date.valueOf(LocalDate.now()));
        entity.setTokenValidation("TOKEN123");
        entity.setStatutByIdStatut(statut);
        entity.setTypeTransactionByIdTypeTransaction(typeTransaction);

        TransactionFonds savedEntity = transactionFondsRepository.save(entity);

        log.info("Saved TransactionFonds: " + savedEntity);

        savedEntity.setMontant(new BigDecimal("750.00"));
        transactionFondsRepository.save(savedEntity);

        TransactionFonds retrievedEntity = transactionFondsRepository.findById(savedEntity.getIdTransactionFonds()).orElse(null);
        log.info("Updated TransactionFonds: " + retrievedEntity);
    }

    public void testGetAll() {
        List<HistoriqueCours> historiqueCoursList = historiqueCoursSyncService.getAllEntities();
        log.info("Found " + historiqueCoursList.size() + " HistoriqueCours records");

        List<TransactionFonds> transactionFondsList = transactionFondsSyncService.getAllEntities();
        log.info("Found " + transactionFondsList.size() + " TransactionFonds records");
    }
}
