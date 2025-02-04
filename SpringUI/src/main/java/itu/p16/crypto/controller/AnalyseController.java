package itu.p16.crypto.controller;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import itu.p16.crypto.service.AnalyseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @Autowired
    private CryptomonnaieRepository cryptoRepository;

    @GetMapping("/transactions")
    public String showTransactionsPage(Model model) {
        List<Cryptomonnaie> cryptos = cryptoRepository.findAll();
        model.addAttribute("cryptos", cryptos);
        return "admin/analyse"; 
    }

    @GetMapping("/transactions/analyse")
    @ResponseBody
    public Map<String, Object> analyseTransactions(
            @RequestParam(required = false) Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax) {
        if (idCrypto != null) {
            return analyseService.getAnalyseTransactions(idCrypto, dateMin, dateMax);
        } else {
            return analyseService.getAnalyseAllTransactions(dateMin, dateMax);
        }
    }

    @GetMapping("/commissions")
    public String showCommissionsPage(Model model) {
        List<Cryptomonnaie> cryptos = cryptoRepository.findAll();
        model.addAttribute("cryptos", cryptos);
        return "admin/analysecomission"; 
    }

    @GetMapping("/commissions/analyse")
    @ResponseBody
    public Map<String, Object> analyseCommissions(
            @RequestParam Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax) {
        return analyseService.getAnalyseCommissions(idCrypto, dateMin, dateMax);
    }
}
