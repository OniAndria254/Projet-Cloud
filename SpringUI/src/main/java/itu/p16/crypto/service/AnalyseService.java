package itu.p16.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.p16.crypto.repository.TransactionCryptoRepository2;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AnalyseService {

    @Autowired
    private TransactionCryptoRepository2 transactionCryptoRepository;

    public Map<String, Object> getAnalyseTransactions(Long idCrypto, LocalDateTime dateMin, LocalDateTime dateMax) {
        return transactionCryptoRepository.analyseTransactions(idCrypto, dateMin, dateMax);
    }

    public Map<String, Object> getAnalyseCommissions(Long idCrypto, LocalDateTime dateMin, LocalDateTime dateMax) {
        return transactionCryptoRepository.analyseCommissions(idCrypto, dateMin, dateMax);
    }

    public Map<String, Object> getAnalyseAllTransactions(LocalDateTime dateMin, LocalDateTime dateMax) {
        return transactionCryptoRepository.analyseAllTransactions(dateMin, dateMax);
    }
}
