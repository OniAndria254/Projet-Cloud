package itu.p16.crypto.firebase.firestore.historique;

import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class HistoriqueCoursDocument implements TimestampedDocument {

    private Integer idHistoriqueCours;
    private BigDecimal prix;
    private String dateEnregistrement;
    private Integer idCryptomonnaie;
    private boolean is_sync_from_firestore;

    private String createdAt;
    private String updatedAt;

    public HistoriqueCoursDocument(HistoriqueCours historique) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idHistoriqueCours = historique.getIdHistoriqueCours();
        this.prix = historique.getPrix();
        this.dateEnregistrement = historique.getDateEnregistrement().toString();
        this.idCryptomonnaie = historique.getIdCryptomonnaie();
        this.is_sync_from_firestore = historique.isSyncFromFirestore();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public HistoriqueCours toEntity() {
        HistoriqueCours historique = new HistoriqueCours();
        historique.setIdHistoriqueCours(idHistoriqueCours);
        historique.setPrix(prix);
        historique.setDateEnregistrement(LocalDate.parse(dateEnregistrement));
        historique.setIdCryptomonnaie(idCryptomonnaie);
        historique.setSyncFromFirestore(is_sync_from_firestore);
        return historique;
    }
}
