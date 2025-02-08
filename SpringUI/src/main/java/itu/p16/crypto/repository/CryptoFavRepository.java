package itu.p16.crypto.repository;

import itu.p16.crypto.entity.CryptoFav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoFavRepository extends JpaRepository<CryptoFav, Integer> {
}
