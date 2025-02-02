package itu.p16.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import itu.p16.crypto.service.AnalyseService;
import itu.p16.crypto.repository.CryptoRepository;
import itu.p16.crypto.model.Crypto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @Autowired
    private CryptoRepository cryptoRepository; // Ajout du repository pour récupérer les cryptos

    /**
     * Affiche la page de sélection pour l'analyse des transactions
     */
    @GetMapping("/transactions")
    public String showAnalyseTransactionsForm(Model model) {
        List<Crypto> cryptos = cryptoRepository.findAll(); // Récupération de toutes les cryptos
        model.addAttribute("cryptos", cryptos);
        return "page/analyseTransactions"; // Page JSP pour afficher le formulaire
    }

    /**
     * Traite l'analyse des transactions et affiche les résultats
     */
    @GetMapping("/transactions/result")
    public String analyseTransactions(
            @RequestParam Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax,
            Model model) {
        Map<String, Object> result = analyseService.getAnalyseTransactions(idCrypto, dateMin, dateMax);
        model.addAttribute("transactions", result);
        return "page/analyseTransactionsResult"; // Page JSP pour afficher les résultats
    }

    /**
     * Affiche la page de sélection pour l'analyse des commissions
     */
    @GetMapping("/commissions")
    public String showAnalyseCommissionsForm(Model model) {
        List<Crypto> cryptos = cryptoRepository.findAll();
        model.addAttribute("cryptos", cryptos);
        return "page/analyseCommissions"; // Page JSP pour afficher le formulaire
    }

    /**
     * Traite l'analyse des commissions et affiche les résultats
     */
    @GetMapping("/commissions/result")
    public String analyseCommissions(
            @RequestParam Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax,
            Model model) {
        Map<String, Object> result = analyseService.getAnalyseCommissions(idCrypto, dateMin, dateMax);
        model.addAttribute("commissions", result);
        return "page/analyseCommissionsResult"; // Page JSP pour afficher les résultats
    }
}
