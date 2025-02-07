package itu.p16.crypto.firebase.firestore.crypto;

import com.google.cloud.Timestamp;
import itu.p16.crypto.entity.Cryptomonnaie;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class CryptomonnaieDocument implements TimestampedDocument {

    private Integer idCryptomonnaie;
    private String nom;
    private String symbole;
    private Timestamp dateCreation;
    private String icon;
    private boolean is_sync_from_firestore;

    private String createdAt;
    private String updatedAt;

    public CryptomonnaieDocument(Cryptomonnaie cryptomonnaie) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.idCryptomonnaie = cryptomonnaie.getIdCryptomonnaie();
        this.nom = cryptomonnaie.getNom();
        this.symbole = cryptomonnaie.getSymbole();
        this.dateCreation = Timestamp.of(Date.from(cryptomonnaie.getDateCreation().atStartOfDay().toInstant(ZoneOffset.UTC)));
        this.icon = cryptomonnaie.getIcon();
        this.is_sync_from_firestore = cryptomonnaie.isSyncFromFirestore();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public Cryptomonnaie toEntity() {
        Cryptomonnaie cryptomonnaie = new Cryptomonnaie();
        cryptomonnaie.setIdCryptomonnaie(idCryptomonnaie);
        cryptomonnaie.setNom(nom);
        cryptomonnaie.setSymbole(symbole);
        cryptomonnaie.setDateCreation(dateCreation.toDate().toInstant().atOffset(ZoneOffset.UTC).toLocalDate());
        cryptomonnaie.setIcon(icon);
        cryptomonnaie.setSyncFromFirestore(is_sync_from_firestore);
        return cryptomonnaie;
    }
}
