package itu.p16.crypto.controller;

import com.example.demo.entity.Commission;
import com.example.demo.repository.CommissionRepository;
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

    @GetMapping("/modifier")
    public String afficherPageModification(Model model) {
        List<Commission> commissions = commissionRepository.findAll();
        model.addAttribute("commissions", commissions);
        return "page/modifierCommission"; 
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

        model.addAttribute("message", "Commission modifiée avec succès !");
        return "page/modifierCommission"; 
    }
}