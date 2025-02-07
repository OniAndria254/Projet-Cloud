package itu.p16.crypto.service;

import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.TransactionCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionCryptoService implements BaseService {
    @Autowired
    private TransactionCryptoRepository transactionCryptoRepository;

    @Override
    public List<TransactionCrypto> findAll() {
        return transactionCryptoRepository.findAll();
    }
}
