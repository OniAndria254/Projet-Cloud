package itu.p16.crypto.service;

import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.generalisation.BaseService;
import itu.p16.crypto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements BaseService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {

        return usersRepository.findAll();
    }
}
