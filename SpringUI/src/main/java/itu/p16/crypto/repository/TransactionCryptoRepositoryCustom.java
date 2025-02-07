package itu.p16.crypto.repository;

import itu.p16.crypto.entity.TransactionCrypto;
import java.util.List;
import java.sql.Timestamp;

public interface TransactionCryptoRepositoryCustom {
    List<TransactionCrypto> getTransactionCryptoByFilter(String requetes, Integer utilisateurId, Integer cryptoId, Timestamp startDate, Timestamp endDate);
}

