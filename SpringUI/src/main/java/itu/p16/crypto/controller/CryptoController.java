package itu.p16.crypto.controller;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    // Endpoint pour insérer des historiques de prix toutes les 10 secondes
    @GetMapping("/update-prices")
    public String updatePrices() {
        try {
            cryptoService.insert10secondes();
            return "Les prix des cryptomonnaies ont été mis à jour avec succès.";
        } catch (Exception e) {
            return "Erreur lors de la mise à jour des prix des cryptomonnaies: " + e.getMessage();
        }
    }

    // Endpoint pour récupérer les derniers historiques de prix
    @GetMapping("/historique")
    public List<HistoriqueCours> getDerniersHistoriques() {
        return cryptoService.getDerniersHistoriques();
    }

    // Endpoint pour récupérer les derniers historiques et mettre à jour les prix
    @GetMapping("/graph")
    public List<HistoriqueCours> getGraph() {
        try {
            return cryptoService.graph();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getcrypto")
    public List<Cryptomonnaie> getCrypto() {
        try {
            return cryptoService.getAllCrypto();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


