package itu.p16.crypto.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "crypto_fav")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CryptoFav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_cryptomonnaie", nullable = false)
    private Integer idCryptomonnaie;

    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;

    @Column(name = "date_ajout", nullable = false)
    private Date dateAjout;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }
}
