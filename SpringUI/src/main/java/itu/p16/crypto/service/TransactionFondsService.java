package itu.p16.crypto.service;

import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.TransactionFondsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionFondsService implements BaseService {
    @Autowired
    private TransactionFondsRepository transactionFondsRepository;

    @Override
    public List<TransactionFonds> findAll() {
        return transactionFondsRepository.findAll();
    }
}
