package itu.p16.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import itu.p16.crypto.repository.CommissionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {

    @Autowired
    private CommissionRepository commissionRepository;

    @PostMapping("/modifier")
    public ResponseEntity<String> modifierCommission(
            @RequestParam Long idCrypto,
            @RequestParam BigDecimal commissionAchat,
            @RequestParam BigDecimal commissionVente) {
        Commission commission = new Commission();
        commission.setIdCryptomonnaie(idCrypto);
        commission.setCommissionAchat(commissionAchat);
        commission.setCommissionVente(commissionVente);
        commission.setDateModification(LocalDateTime.now());
        commissionRepository.save(commission);
        return ResponseEntity.ok("Commission modifiée avec succès");
    }
}