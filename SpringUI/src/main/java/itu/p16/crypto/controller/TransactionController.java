package itu.p16.crypto.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.entity.Statut;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.entity.TypeTransaction;
import itu.p16.crypto.repository.CryptomonnaieRepository;
import itu.p16.crypto.repository.HistoriqueCoursRepository;
import itu.p16.crypto.repository.PortefeuilleCryptoRepository;
import itu.p16.crypto.repository.PortefeuilleRepository;
import itu.p16.crypto.repository.TransactionCryptoRepository;
import itu.p16.crypto.repository.TransactionFondsRepository;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private CryptomonnaieRepository cryptoRepo;

    @Autowired
    private TransactionCryptoRepository transactionRepo;

    @Autowired
    private PortefeuilleCryptoRepository portefeuilleCryptoRepository;

    @Autowired
    private PortefeuilleRepository portefeuilleRepo;

    @Autowired
    private TransactionFondsRepository transactionFondsRepository;
    
    @Autowired
    private HistoriqueCoursRepository historiqueCoursRepo;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("page/error"); // Vue d'erreur
        // (ex: error.jsp)
        modelAndView.addObject("errorMessage", "Une erreur s'est produite lors dutraitement de votre requête.");
        modelAndView.addObject("errorDetails", ex.getMessage());
        return modelAndView;
    }

    // page de depot et retrait
    @GetMapping("/depositWithdraw")
    public String showDepositWithdrawPage(Model model) {
        Integer id_user = 1;
        BigDecimal balance = portefeuilleRepo.findSoldeByUtilisateur(id_user); // Exemple avec l'utilisateur 1
        List<TransactionFonds> demandes = transactionFondsRepository.findByUserId(id_user);
        model.addAttribute("demandes", demandes);
        model.addAttribute("balance", balance);
        return "page/transactionFond";
    }


    // achat et vente de crypto
    @GetMapping("/buy-sell")
    public String showBuySellPage(Model model) {

         List<Cryptomonnaie> cryptos = cryptoRepo.findAll();
            if (cryptos.isEmpty()) {
                throw new RuntimeException("Aucune cryptomonnaie trouvée dans la base de données.");
            }
            for (Cryptomonnaie c : cryptos) {
                BigDecimal latestPrice = historiqueCoursRepo.findLatestPriceByCryptoId(c.getIdCryptomonnaie());
                c.setCurrentPrice(latestPrice);
            }
            model.addAttribute("cryptos", cryptos);
            return "page/transactionCrypto";
    }

    @PostMapping("/buy")
    public String processBuy(@RequestParam("cryptoId") Integer cryptoId,
            @RequestParam("quantity") BigDecimal quantity,
            @RequestParam("price") BigDecimal price,
            Model model) {
        try {
            // Calcul du montant total
            BigDecimal total = quantity.multiply(price);
            Integer idUtilisateur = 1;
            Integer typeTransaction = 3;
            Date dateTransaction = new Date(System.currentTimeMillis());

            // Gestion du portefeuille_crypto : s'il existe, on met à jour, sinon on insère
            BigDecimal quantiteExistante = portefeuilleCryptoRepository
                    .findQuantiteByUtilisateurAndCryptomonnaie(idUtilisateur, cryptoId);
            if (quantiteExistante != null) {
                portefeuilleCryptoRepository.updateQuantiteForBuy(idUtilisateur, cryptoId, quantity);
            } else {
                portefeuilleCryptoRepository.insertIntoPortefeuilleCrypto(idUtilisateur, quantity, cryptoId);
            }

            // Insertion de la transaction dans transaction_crypto
            transactionRepo.insertTransactionCrypto(idUtilisateur, quantity, price, total, dateTransaction,
                    typeTransaction, cryptoId);

            // Mise à jour du solde du portefeuille (solde = solde - total)
            portefeuilleRepo.updateSoldeForBuy(idUtilisateur, total);

            model.addAttribute("success", "Votre vente a ete fait.");
        } catch (Exception ex) {
            model.addAttribute("error", "Erreur lors de la vente: " + ex.getMessage());
        }
        return "redirect:/transaction/buy-sell";
    }

    @PostMapping("/sell")
    public String processSell(@RequestParam("cryptoId") Integer cryptoId,
            @RequestParam("quantity") BigDecimal quantity,
            @RequestParam("price") BigDecimal price,
            Model model) {
        try {
            BigDecimal total = quantity.multiply(price);
            Integer idUtilisateur = 1;
            Integer typeTransaction = 4; // Vente
            Date dateTransaction = new Date(System.currentTimeMillis());

            BigDecimal quantiteExistante = portefeuilleCryptoRepository
                    .findQuantiteByUtilisateurAndCryptomonnaie(idUtilisateur, cryptoId);
            if (quantiteExistante != null) {

                portefeuilleCryptoRepository.updateQuantiteForSell(idUtilisateur, cryptoId, quantity);
            } else {

                // Vous pouvez lever une exception à ce stade :
                throw new RuntimeException("Quantité insuffisante dans votre portefeuille crypto pour vendre.");
            }

            transactionRepo.insertTransactionCrypto(idUtilisateur, quantity, price, total, dateTransaction,
                    typeTransaction, cryptoId);

            portefeuilleRepo.updateSoldeForSell(idUtilisateur, total);

            model.addAttribute("success", "Votre vente a ete fait.");
        } catch (Exception ex) {
            model.addAttribute("error", "Erreur lors de la vente: " + ex.getMessage());
        }
        return "redirect:/transaction/buy-sell";
    }

    @PostMapping("/deposit")
    public String processDeposit(@RequestParam("amount") BigDecimal amount, Model model) {
        try {
            Integer idUtilisateur = 1;
            BigDecimal balance = portefeuilleRepo.findSoldeByUtilisateur(idUtilisateur); // Exemple avec l'utilisateur 1
            model.addAttribute("balance", balance);

        
            List<TransactionFonds> demandes = transactionFondsRepository.findByUserId(idUtilisateur);
            model.addAttribute("demandes", demandes);
            if (amount == null) {

                model.addAttribute("error", "veuiller mettre un montant valide");
                return "page/transactionFond";
            }

            List<Portefeuille> portefeuilles = portefeuilleRepo.findByIdUtilisateur(idUtilisateur);

            if (portefeuilles == null || portefeuilles.isEmpty()) {
                // Création d'un nouveau portefeuille avec solde initial à 0
                Portefeuille nouveauPortefeuille = new Portefeuille();
                nouveauPortefeuille.setIdUtilisateur(idUtilisateur);
                nouveauPortefeuille.setSolde(BigDecimal.ZERO);
                nouveauPortefeuille.setDateCreation(new Date(System.currentTimeMillis()));
                portefeuilleRepo.save(nouveauPortefeuille);
      
            }
            TypeTransaction typeTransaction = new TypeTransaction();
            typeTransaction.setIdTypeTransaction(1); // 1 = dépôt (selon vos données)
            typeTransaction.setNom("depot");

            Statut statutEnAttente = new Statut();
            statutEnAttente.setIdStatut(1); // 1 = en attente
            statutEnAttente.setNom("en attente");

            Date dateTransaction = new Date(System.currentTimeMillis());

            TransactionFonds tf = new TransactionFonds();
            tf.setIdUtilisateur(idUtilisateur);
            tf.setMontant(amount);
            tf.setDateTransaction(dateTransaction);
            tf.setTokenValidation(null);
            tf.setTypeTransactionByIdTypeTransaction(typeTransaction);
            tf.setStatutByIdStatut(statutEnAttente);

            transactionFondsRepository.save(tf);
            model.addAttribute("success", "Votre demande de dépôt a été soumise et est en attente de validation.");
        } catch (Exception ex) {
            model.addAttribute("error", "Erreur lors de la demande de dépôt : " + ex.getMessage());
        }
        return "page/transactionFond";
    }

    @PostMapping("/withdraw")
    public String processWithdraw(@RequestParam("amount") BigDecimal amount, Model model) {
        try {
            Integer idUtilisateur = 1;
            BigDecimal balance = portefeuilleRepo.findSoldeByUtilisateur(idUtilisateur); // Exemple avec l'utilisateur 1
            model.addAttribute("balance", balance);

            List<TransactionFonds> demandes = transactionFondsRepository.findByUserId(idUtilisateur);
            model.addAttribute("demandes", demandes);


            List<Portefeuille> portefeuilles = portefeuilleRepo.findByIdUtilisateur(idUtilisateur);

            if (portefeuilles == null || portefeuilles.isEmpty()) {
                model.addAttribute("error","veuiller deposer avant");
                return "page/transactionFond";
            }


            if (amount.compareTo(balance) > 0) {

                model.addAttribute("error", "Solde insuffisant pour effectuer ce retrait.");
                return "page/transactionFond";
            }

            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {

                model.addAttribute("veuiller mettre un montant valide");
                return "page/transactionFond";
            }

            TypeTransaction typeTransaction = new TypeTransaction();
            typeTransaction.setIdTypeTransaction(2); // 2 = retrait
            typeTransaction.setNom("retrait");

            Statut statutEnAttente = new Statut();
            statutEnAttente.setIdStatut(1); // 1 = en attente
            statutEnAttente.setNom("en attente");

            Date dateTransaction = new Date(System.currentTimeMillis());

            TransactionFonds tf = new TransactionFonds();
            tf.setIdUtilisateur(idUtilisateur);
            tf.setMontant(amount);
            tf.setDateTransaction(dateTransaction);
            tf.setTokenValidation(null);
            tf.setTypeTransactionByIdTypeTransaction(typeTransaction);
            tf.setStatutByIdStatut(statutEnAttente);

            transactionFondsRepository.save(tf);

            model.addAttribute("success", "Votre demande de retrait a été soumise et est en attente de validation.");
        } catch (Exception ex) {
            model.addAttribute("error", "Erreur lors de la demande de retrait: " + ex.getMessage());
        }
        return "page/transactionFond";
    }

}
