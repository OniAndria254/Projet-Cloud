package itu.p16.crypto.service;

import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.entity.PortefeuilleCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.PortefeuilleCryptoRepository;
import itu.p16.crypto.repository.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortefeuilleCryptoService implements BaseService {
    @Autowired
    PortefeuilleCryptoRepository portefeuilleCryptoRepository;


    @Override
    public List<PortefeuilleCrypto> findAll() {
        return portefeuilleCryptoRepository.findAll();
    }
}
