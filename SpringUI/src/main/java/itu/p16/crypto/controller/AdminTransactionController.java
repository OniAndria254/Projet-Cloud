package itu.p16.crypto.controller;

import java.util.List;

import itu.p16.crypto.exception.NoUserLoggedException;
import itu.p16.crypto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.entity.Statut;
import itu.p16.crypto.entity.TypeTransaction;
import itu.p16.crypto.repository.PortefeuilleRepository;
import itu.p16.crypto.repository.TransactionFondsRepository;

@Controller
@RequestMapping("/admin")
public class AdminTransactionController {

    @Autowired
    private TransactionFondsRepository transactionFondsRepository;
    
    @Autowired
    private PortefeuilleRepository portefeuilleRepo;

    @Autowired
    private AuthService authService;
    /**
     * Affiche la liste des demandes (dépôt et retrait) en attente.
     * On suppose que le statut "en attente" est défini avec l'ID 1.
     */
    @GetMapping("/transactions")
    public String showPendingTransactions(Model model) throws NoUserLoggedException {
        authService.requireUser();
        List<TransactionFonds> demandes = transactionFondsRepository.findByStatutId(1);
        model.addAttribute("demandes", demandes);
        return "page/adminTransactions"; // JSP pour l'administration
    }
    
    /**
     * Permet à l'administrateur de valider ou refuser une demande.
     * Paramètre "action" attendu : "accept" ou "refuse".
     */
    @PostMapping("/validateTransaction")
    public String validateTransaction(@RequestParam("transactionId") Integer transactionId,
                                      @RequestParam("action") String action,
                                      Model model) throws NoUserLoggedException {
        authService.requireUser();
        try {
            TransactionFonds tf = transactionFondsRepository.findById(transactionId)
                    .orElseThrow(() -> new RuntimeException("Transaction non trouvée"));
            
            if ("accept".equalsIgnoreCase(action)) {
                // Pour un dépôt accepté, ajoutez le montant au solde du portefeuille.
                // Pour un retrait accepté, soustrayez le montant.
                if (tf.getTypeTransactionByIdTypeTransaction().getNom().equalsIgnoreCase("depot")) {
                    portefeuilleRepo.updateSoldeForSell(tf.getIdUtilisateur(), tf.getMontant());
                } else if (tf.getTypeTransactionByIdTypeTransaction().getNom().equalsIgnoreCase("retrait")) {
                    portefeuilleRepo.updateSoldeForBuy(tf.getIdUtilisateur(), tf.getMontant());
                }
                // Mettre à jour le statut à "accepté" (par exemple, 2)
                Statut statutAccepte = new Statut();
                statutAccepte.setIdStatut(2); // 2 = accepté
                statutAccepte.setNom("accepté");
                tf.setStatutByIdStatut(statutAccepte);
            } else if ("refuse".equalsIgnoreCase(action)) {
                // Mettre à jour le statut à "refusé" (par exemple, 3)
                Statut statutRefuse = new Statut();
                statutRefuse.setIdStatut(3); // 3 = refusé
                statutRefuse.setNom("refusé");
                tf.setStatutByIdStatut(statutRefuse);
            }
            transactionFondsRepository.save(tf);
            model.addAttribute("successMessage", "La demande a été " + ("accept".equalsIgnoreCase(action) ? "acceptée" : "refusée") + " avec succès.");
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Erreur lors de la validation : " + ex.getMessage());
        }
        
        List<TransactionFonds> demandes = transactionFondsRepository.findByStatutId(1);
        model.addAttribute("demandes", demandes);
        return "page/adminTransactions";
    }
}
