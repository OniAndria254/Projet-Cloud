package itu.p16.crypto.service;

import itu.p16.crypto.entity.CryptoFav;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.CryptoFavRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoFavService implements BaseService {

    private final CryptoFavRepository cryptoFavRepository;

    @Override
    public List findAll() {
        return cryptoFavRepository.findAll();
    }
}
