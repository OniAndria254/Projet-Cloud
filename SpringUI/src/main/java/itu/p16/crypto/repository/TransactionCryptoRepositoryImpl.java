package itu.p16.crypto.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import itu.p16.crypto.entity.TransactionCrypto;
import java.util.List;
import java.sql.Timestamp;

@Repository
public class TransactionCryptoRepositoryImpl implements TransactionCryptoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TransactionCrypto> getTransactionCryptoByFilter(String requetes, Integer utilisateurId, Integer cryptoId, Timestamp startDate, Timestamp endDate) {
        Query query = entityManager.createQuery(requetes, TransactionCrypto.class);

        // Passer les paramètres dans la requête
        if (utilisateurId != null) {
            query.setParameter("utilisateurId", utilisateurId);
        }

        if (cryptoId != null) {
            query.setParameter("cryptoId", cryptoId);
        }

        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }

        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        return query.getResultList();
    }
}


