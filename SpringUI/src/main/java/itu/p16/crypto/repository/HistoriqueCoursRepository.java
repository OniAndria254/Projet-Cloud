package itu.p16.crypto.repository;

import itu.p16.crypto.entity.HistoriqueCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueCoursRepository extends JpaRepository<HistoriqueCours, Integer> {


    @Query(value = "SELECT prix FROM historique_cours hc WHERE hc.id_cryptomonnaie = :idCryptomonnaie ORDER BY hc.id_historique_cours DESC LIMIT 1", nativeQuery = true)
    BigDecimal getDernierPrixCrypto(@Param("idCryptomonnaie") Integer idCryptomonnaie);

    @Query(value = "SELECT * FROM historique_cours ORDER BY date_enregistrement DESC, id_historique_cours DESC LIMIT :nombreCrypto", nativeQuery = true)
    List<HistoriqueCours> getDerniersHistoriques(@Param("nombreCrypto") int nombreCrypto);
    



    
    // @Query("SELECT prix FROM historique_cours WHERE id_cryptomonnaie = :idCrypto ORDER BY id_historique_cours DESC LIMIT 1")
    // BigDecimal getDernierPrixCrypto(@Param("idCrypto") int idCrypto);
}
