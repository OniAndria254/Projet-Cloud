package itu.p16.crypto.service;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import itu.p16.crypto.repository.HistoriqueCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CryptoService {

    private final Random random = new Random();
    private final CryptomonnaieRepository cryptomonnaieRepository;
    private final HistoriqueCoursRepository historiqueCoursRepository;

    @Autowired
    public CryptoService(CryptomonnaieRepository cryptomonnaieRepository, HistoriqueCoursRepository historiqueCoursRepository) {
        this.cryptomonnaieRepository = cryptomonnaieRepository;
        this.historiqueCoursRepository = historiqueCoursRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void insert10secondes() {
        List<Cryptomonnaie> cryptomonnaies = cryptomonnaieRepository.findAll();
    
        for (Cryptomonnaie crypto : cryptomonnaies) {
            BigDecimal dernierPrix = getDernierPrixCrypto(crypto.getIdCryptomonnaie());
    
            BigDecimal nouveauPrix;
            if (dernierPrix != null) {
                double pourcentage = random.nextDouble() * (30 - 5) + 5; // Entre 5% et 30%
                boolean isPositif = random.nextBoolean();
    
                if (isPositif) {
                    nouveauPrix = dernierPrix.add(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                } else {
                    nouveauPrix = dernierPrix.subtract(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                    // Assurer que le prix ne descend pas en dessous de 10
                    if (nouveauPrix.compareTo(BigDecimal.valueOf(10)) < 0) {
                        nouveauPrix = BigDecimal.valueOf(10);
                    }
                }
            } else {
                // Si aucun prix précédent, initialiser à une valeur par défaut
                nouveauPrix = BigDecimal.valueOf(5000);
            }
    
            // Enregistrer dans l'historique
            HistoriqueCours historique = new HistoriqueCours();
            historique.setIdCryptomonnaie(crypto.getIdCryptomonnaie());
            historique.setPrix(nouveauPrix);
            historique.setDateEnregistrement(LocalDateTime.now().toLocalDate());
    
            historiqueCoursRepository.save(historique);
        }
    }

    private BigDecimal getDernierPrixCrypto(Integer idCryptomonnaie) {
        BigDecimal historiques = historiqueCoursRepository.getDernierPrixCrypto(idCryptomonnaie);
        return historiques; // Aucun historique pour cette cryptomonnaie
    
    
    }
    

    // public List<HistoriqueCours> getDerniersHistoriques() {
    //     List<HistoriqueCours> historiques = new ArrayList<>();
    //     List<Cryptomonnaie> cryptomonnaies = cryptomonnaieRepository.findAll();

    //     for (Cryptomonnaie crypto : cryptomonnaies) {
    //         List<HistoriqueCours> historiquesForCrypto = historiqueCoursRepository.getDernierPrixCrypto(crypto.getIdCryptomonnaie());
    //         historiques.addAll(historiquesForCrypto);
    //     }

    //     return historiques != null ? historiques : new ArrayList<>();
    // }
    public List<HistoriqueCours> getDerniersHistoriques() {
        
        List<Cryptomonnaie> cryptomonnaies = cryptomonnaieRepository.findAll();
        List<HistoriqueCours> historiques = historiqueCoursRepository.getDerniersHistoriques(cryptomonnaies.size());
        return historiques != null ? historiques : new ArrayList<>();
    }

    public List<Cryptomonnaie> getAllCrypto() {
        
        List<Cryptomonnaie> cryptomonnaies = cryptomonnaieRepository.findAll();
        return cryptomonnaies != null ? cryptomonnaies : new ArrayList<>();
    }
}

