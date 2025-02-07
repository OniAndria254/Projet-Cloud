package itu.p16.crypto.service;

import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortefeuilleService implements BaseService {
    @Autowired
    PortefeuilleRepository portefeuilleRepository;


    @Override
    public List<Portefeuille> findAll() {
        return portefeuilleRepository.findAll();
    }
}
