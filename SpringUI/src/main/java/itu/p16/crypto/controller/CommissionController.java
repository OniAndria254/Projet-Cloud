package itu.p16.crypto.controller;

import itu.p16.crypto.entity.Commission;
import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.repository.CommissionRepository;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/commission")
public class CommissionController {

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private CryptomonnaieRepository cryptomonnaieRepository;

    @GetMapping("/modifier")
    public String afficherPageModification(Model model) {
        List<Commission> commissions = commissionRepository.findAll();
        List<Cryptomonnaie> crypto=cryptomonnaieRepository.findAll();
        model.addAttribute("crypto",crypto);
        model.addAttribute("commissions", commissions);
        return "admin/commission";
    }

    @PostMapping("/modifier")
    public String modifierCommission(
            @RequestParam Long idCrypto,
            @RequestParam BigDecimal commissionAchat,
            @RequestParam BigDecimal commissionVente,
            Model model) {
        Commission commission = new Commission();
        commission.setIdCryptomonnaie(idCrypto);
        commission.setCommissionAchat(commissionAchat);
        commission.setCommissionVente(commissionVente);
        commission.setDateModification(LocalDateTime.now());
        commissionRepository.save(commission);
        List<Commission> commissions = commissionRepository.findAll();
        List<Cryptomonnaie> crypto=cryptomonnaieRepository.findAll();
        model.addAttribute("crypto",crypto);
        model.addAttribute("commissions", commissions);

        model.addAttribute("message", "Commission modifiée avec succès !");
        return "admin/commission";
    }
}