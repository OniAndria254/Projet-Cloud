package itu.p16.crypto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import itu.p16.crypto.entity.*;


public interface CryptomonnaieRepository extends JpaRepository<Cryptomonnaie, Long> {
    
}
