package itu.p16.crypto.controller;

import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.service.TransactionCryptoService;
import itu.p16.crypto.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionCryptoController {

    private final TransactionCryptoService transactionService;

    public TransactionCryptoController(TransactionCryptoService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/histotransaction")
    public String filterTransactions(){
        return "page/histotransaction";
    }


    @GetMapping("/filter")
        public List<TransactionCrypto> filterTransactions(
        @RequestParam(value = "startDate", required = false) String startDateStr,
        @RequestParam(value = "endDate", required = false) String endDateStr,
        @RequestParam(value = "utilisateurId", required = false) Integer utilisateurId,
        @RequestParam(value = "cryptoId", required = false) Integer cryptoId) {

    // Conversion des chaînes en Timestamp si elles sont présentes, sinon les laisser null
        Timestamp startDate = null;
        Timestamp endDate = null;
        Integer utilisateurIdv = null;
        Integer cryptoIdv = null;

        if (startDateStr != null && !startDateStr.isEmpty()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
            // Conversion de la chaîne d'entrée en LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(startDateStr, inputFormatter);
    
            // Formatage dans le format souhaité
            String output = dateTime.format(outputFormatter);
            startDate = Timestamp.valueOf(output);
            startDate.setNanos(0);
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
            // Conversion de la chaîne d'entrée en LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(endDateStr, inputFormatter);
    
            // Formatage dans le format souhaité
            String output = dateTime.format(outputFormatter);
            endDate = Timestamp.valueOf(output);
            endDate.setNanos(0);
        }

        if (utilisateurId != null) {
            utilisateurIdv = utilisateurId;
        }
        if (cryptoId != null) {
            cryptoIdv = cryptoId;
        }
        return transactionService.filterTransactions(startDate, endDate, utilisateurIdv, cryptoIdv);
    }



    @GetMapping("/all")
    public List<TransactionCrypto> getAllTransactions() {
        return transactionService.findAll();
    }
}
