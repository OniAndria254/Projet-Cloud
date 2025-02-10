package itu.p16.crypto.firebase.firestore.transactioncrypto;

import com.google.cloud.Timestamp;
import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionCryptoDocument implements TimestampedDocument {

    private Integer idTransactionCrypto;
    private String idUtilisateur;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal montantTotal;
    private String dateTransaction;
    private Integer idTypeTransaction;
    private Integer idCryptomonnaie;

    private String createdAt;
    private String updatedAt;

    public TransactionCryptoDocument(TransactionCrypto transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idTransactionCrypto = transaction.getIdTransactionCrypto();
        this.idUtilisateur = String.valueOf(transaction.getIdUtilisateur());
        this.quantite = transaction.getQuantite();
        this.prixUnitaire = transaction.getPrixUnitaire();
        this.montantTotal = transaction.getMontantTotal();
        this.dateTransaction = dateFormat.format(transaction.getDateTransaction());

        this.idTypeTransaction = transaction.getIdTypeTransaction();

        this.idCryptomonnaie = transaction.getIdCryptomonnaie();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public TransactionCrypto toEntity() {
        TransactionCrypto transaction = new TransactionCrypto();
        transaction.setIdTransactionCrypto(idTransactionCrypto);
        transaction.setIdUtilisateur(Integer.valueOf(idUtilisateur));
        transaction.setIdTypeTransaction(idTypeTransaction);
        transaction.setIdCryptomonnaie(idCryptomonnaie);
        transaction.setQuantite(quantite);
        transaction.setPrixUnitaire(prixUnitaire);
        transaction.setMontantTotal(montantTotal);
        transaction.setDateTransaction(java.sql.Date.valueOf(dateTransaction.split(" ")[0]));

        return transaction;
    }
}
