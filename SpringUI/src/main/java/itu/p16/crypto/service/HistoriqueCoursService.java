package itu.p16.crypto.service;

import itu.p16.crypto.entity.HistoriqueCours;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.HistoriqueCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueCoursService implements BaseService {
    @Autowired
    private HistoriqueCoursRepository historiqueCoursRepository;

    @Override
    public List<HistoriqueCours> findAll() {
        return historiqueCoursRepository.findAll();
    }
}
