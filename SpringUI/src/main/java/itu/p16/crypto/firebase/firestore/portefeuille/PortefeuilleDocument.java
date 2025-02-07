package itu.p16.crypto.firebase.firestore.portefeuille;

import com.google.cloud.Timestamp;
import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class PortefeuilleDocument implements TimestampedDocument {

    private Integer idPortefeuille;
    private BigDecimal solde;
    private Timestamp dateCreation;
    private Integer idUtilisateur;
    private boolean is_sync_from_firestore;


    private String createdAt;
    private String updatedAt;

    public PortefeuilleDocument(Portefeuille portefeuille) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idPortefeuille = portefeuille.getIdPortefeuille();
        this.solde = portefeuille.getSolde();
        this.dateCreation = Timestamp.of(portefeuille.getDateCreation());
        this.idUtilisateur = portefeuille.getIdUtilisateur();

        this.is_sync_from_firestore = portefeuille.isSyncFromFirestore();
        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public Portefeuille toEntity() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.setIdPortefeuille(idPortefeuille);
        portefeuille.setSolde(solde);
        portefeuille.setDateCreation(new java.sql.Date(dateCreation.toDate().getTime()));

//        portefeuille.setDateCreation((java.sql.Date) new Date(dateCreation.toDate().getTime()));
        portefeuille.setIdUtilisateur(idUtilisateur);
        portefeuille.setSyncFromFirestore(is_sync_from_firestore);
        return portefeuille;
    }
}
