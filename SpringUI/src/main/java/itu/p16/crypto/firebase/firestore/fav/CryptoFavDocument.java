package itu.p16.crypto.firebase.firestore.fav;

import com.google.cloud.Timestamp;
import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;

@Data
@NoArgsConstructor
public class CryptoFavDocument implements TimestampedDocument {

    private Integer id;  // ID Firestore (peut être String)
    private Integer idCryptomonnaie;
    private Integer idUtilisateur;
    private Date dateAjout;
    private boolean is_sync_from_firestore;

    private String createdAt;
    private String updatedAt;

    public CryptoFavDocument(CryptoFav cryptoFav) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.id = cryptoFav.getId();
        this.idCryptomonnaie = cryptoFav.getIdCryptomonnaie();
        this.idUtilisateur = cryptoFav.getIdUtilisateur();
        this.dateAjout = cryptoFav.getDateAjout();

        this.is_sync_from_firestore = cryptoFav.isSyncFromFirestore();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public CryptoFav toEntity() {
        CryptoFav cryptoFav = new CryptoFav();
        cryptoFav.setId(id);
        cryptoFav.setIdCryptomonnaie(idCryptomonnaie);
        cryptoFav.setIdUtilisateur(idUtilisateur);
        cryptoFav.setDateAjout((java.sql.Date) dateAjout);
        cryptoFav.setSyncFromFirestore(is_sync_from_firestore);
        return cryptoFav;
    }
}
