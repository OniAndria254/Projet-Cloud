package itu.p16.crypto.firebase.firestore.portefeuille;

import com.google.cloud.firestore.annotation.DocumentId;
import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.entity.PortefeuilleCrypto;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PortefeuilleCryptoDocument {

    @DocumentId
    private String idPortefeuilleCrypto;
    private Integer idUtilisateur;
    private BigDecimal quantite;
    private Integer cryptomonnaieId;  // Référence à l'ID de la cryptomonnaie
    private boolean is_sync_from_firestore;

    private String createdAt;
    private String updatedAt;


    public PortefeuilleCryptoDocument(PortefeuilleCrypto portefeuilleCrypto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idPortefeuilleCrypto = String.valueOf(portefeuilleCrypto.getIdPortefeuilleCrypto());
        this.idUtilisateur = portefeuilleCrypto.getIdUtilisateur();
        this.quantite = portefeuilleCrypto.getQuantite();
        this.cryptomonnaieId = portefeuilleCrypto.getIdCryptomonnaie();
        this.is_sync_from_firestore = portefeuilleCrypto.isSyncFromFirestore();
        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public PortefeuilleCrypto toEntity() {
        PortefeuilleCrypto portefeuilleCrypto = new PortefeuilleCrypto();
        portefeuilleCrypto.setIdPortefeuilleCrypto(Integer.valueOf(this.idPortefeuilleCrypto));
        portefeuilleCrypto.setIdUtilisateur(this.idUtilisateur);
        portefeuilleCrypto.setQuantite(this.quantite);
        portefeuilleCrypto.setIdCryptomonnaie(this.cryptomonnaieId);
        portefeuilleCrypto.setSyncFromFirestore(this.is_sync_from_firestore);
        return portefeuilleCrypto;
    }
}
