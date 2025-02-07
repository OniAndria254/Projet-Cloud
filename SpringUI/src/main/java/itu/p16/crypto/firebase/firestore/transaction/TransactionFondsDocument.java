package itu.p16.crypto.firebase.firestore.transaction;

import itu.p16.crypto.entity.Statut;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.entity.TypeTransaction;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionFondsDocument implements TimestampedDocument {

    private Integer idTransactionFonds;
    private Integer idUtilisateur;
    private BigDecimal montant;
    private String dateTransaction;
    private String tokenValidation;
    private Integer idStatut;
    private Integer idTypeTransaction;

    private String createdAt;
    private String updatedAt;

    public TransactionFondsDocument(TransactionFonds transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idTransactionFonds = transaction.getIdTransactionFonds();
        this.idUtilisateur = transaction.getIdUtilisateur();
        this.montant = transaction.getMontant();
        this.dateTransaction = transaction.getDateTransaction().toString();
        this.tokenValidation = transaction.getTokenValidation();
        this.idStatut = transaction.getStatutByIdStatut().getIdStatut();
        this.idTypeTransaction = transaction.getTypeTransactionByIdTypeTransaction().getIdTypeTransaction();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public TransactionFonds toEntity() {
        TransactionFonds transaction = new TransactionFonds();
        transaction.setIdTransactionFonds(idTransactionFonds);
        transaction.setIdUtilisateur(idUtilisateur);
        transaction.setMontant(montant);
        transaction.setDateTransaction(java.sql.Date.valueOf(dateTransaction));
        transaction.setTokenValidation(tokenValidation);

        Statut statut = new Statut();
        statut.setIdStatut(idStatut);
        transaction.setStatutByIdStatut(statut);

        TypeTransaction typeTransaction = new TypeTransaction();
        typeTransaction.setIdTypeTransaction(idTypeTransaction);
        transaction.setTypeTransactionByIdTypeTransaction(typeTransaction);

        return transaction;
    }
}
