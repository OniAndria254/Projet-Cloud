package itu.p16.crypto.service;

import itu.p16.crypto.entity.TransactionSummaryDTO;
import itu.p16.crypto.repository.TransactionCryptoRepository3;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionCryptoRepository3 transactionCryptoRepository;

    public TransactionService(TransactionCryptoRepository3 transactionCryptoRepository) {
        this.transactionCryptoRepository = transactionCryptoRepository;
    }

    public List<TransactionSummaryDTO> getTransactionSummary() {
        return transactionCryptoRepository.getTransactionSummary();
    }

    public List<TransactionSummaryDTO> getTransactionSummaryFilteredByDate(String dateMax) {
        return transactionCryptoRepository.getTransactionSummaryFilteredByDate(dateMax);
    }
}
