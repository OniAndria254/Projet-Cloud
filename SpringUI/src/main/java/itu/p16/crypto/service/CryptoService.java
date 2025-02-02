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

    public void insert10secondes() {
        List<Cryptomonnaie> cryptomonnaies = cryptomonnaieRepository.findAll();

        for (Cryptomonnaie crypto : cryptomonnaies) {
            BigDecimal dernierPrix = getDernierPrixCrypto(crypto.getIdCryptomonnaie());

            BigDecimal nouveauPrix;
            if (dernierPrix != null) {
                double pourcentage = random.nextDouble() * (20 - 2) + 2; // Entre 2% et 20%
                boolean isPositif = random.nextBoolean();

                if (isPositif) {
                    nouveauPrix = dernierPrix.add(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                } else {
                    nouveauPrix = dernierPrix.subtract(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                }
            } else {
                nouveauPrix = BigDecimal.valueOf(100);
            }

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
    

    public List<HistoriqueCours> graph() throws Exception {
        List<HistoriqueCours> derniersHistoriques = null;
        try {
            insert10secondes();
            derniersHistoriques = getDerniersHistoriques();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return derniersHistoriques;
    }
}

