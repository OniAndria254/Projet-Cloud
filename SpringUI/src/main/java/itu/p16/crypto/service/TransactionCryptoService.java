package itu.p16.crypto.service;

import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.TransactionCryptoRepository;
import itu.p16.crypto.repository.TransactionCryptoRepositoryImpl;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Timestamp;

@Service
public class TransactionCryptoService implements BaseService {
    @Autowired
    private TransactionCryptoRepository transactionCryptoRepository;
    @Autowired
    private EntityManager entityManager;
    private final TransactionCryptoRepositoryImpl tc;

    public TransactionCryptoService(TransactionCryptoRepository transactionCryptoRepository, TransactionCryptoRepositoryImpl tc2) {
        this.transactionCryptoRepository = transactionCryptoRepository;
        this.tc = tc2;
    }

    public List<TransactionCrypto> filterTransactions(Timestamp startDate, Timestamp endDate, Integer utilisateurId, Integer cryptoId) {
        List<TransactionCrypto> valiny = null;
        String query = filterTransactionsQuery(utilisateurId,cryptoId,startDate,endDate);
        System.out.println("QUERY = "+query);
        valiny = tc.getTransactionCryptoByFilter(query,utilisateurId,cryptoId,startDate,endDate);
        return valiny;
    }

    public String filterTransactionsQuery(Integer utilisateurId, Integer cryptoId, Timestamp startDate, Timestamp endDate) {
        StringBuilder queryBuilder = new StringBuilder("SELECT t FROM TransactionCrypto t WHERE 1=1");
    
        if (utilisateurId != null) {
            queryBuilder.append(" AND t.idUtilisateur = :utilisateurId");
        }
    
        if (cryptoId != null) {
            queryBuilder.append(" AND t.idCryptomonnaie = :cryptoId");
        }
    
        if (startDate != null) {
            queryBuilder.append(" AND t.dateTransaction >= :startDate");
        }
    
        if (endDate != null) {
            queryBuilder.append(" AND t.dateTransaction <= :endDate");
        }
    
        return queryBuilder.toString();  // Retourne la requête avec des paramètres
    }

    @Override
    public List<TransactionCrypto> findAll() {
        return transactionCryptoRepository.findAll();
    }
}
