package itu.p16.crypto.service;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptomonnaieService  implements BaseService {
    @Autowired
    private CryptomonnaieRepository cryptomonnaieRepository;

    @Override
    public List<Cryptomonnaie> findAll() {
        return cryptomonnaieRepository.findAll();
    }
}
