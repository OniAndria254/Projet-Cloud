package itu.p16.crypto.service;

import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PortefeuilleService implements BaseService {
    @Autowired
    PortefeuilleRepository portefeuilleRepository;

    @Transactional
    public void updateSoldeForSell(Integer idUtilisateur, BigDecimal montant) {
        Portefeuille portefeuille = portefeuilleRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Portefeuille non trouvé"));
        portefeuille.setSolde(portefeuille.getSolde().add(montant));
        portefeuilleRepository.save(portefeuille); // Déclenche @PostUpdate
    }

    @Transactional
    public void updateSoldeForBuy(Integer idUtilisateur, BigDecimal montant) {
        Portefeuille portefeuille = portefeuilleRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Portefeuille non trouvé"));

        if (portefeuille.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant pour l'achat");
        }

        portefeuilleRepository.updateSoldeForBuy(idUtilisateur, montant);

        // Charger à nouveau après mise à jour pour synchroniser Firestore
        portefeuille.setSolde(portefeuille.getSolde().subtract(montant));
        portefeuilleRepository.save(portefeuille);
//        portefeuilleSyncService.updateAsDocument(portefeuille);
    }



    @Override
    public List<Portefeuille> findAll() {
        return portefeuilleRepository.findAll();
    }
}
