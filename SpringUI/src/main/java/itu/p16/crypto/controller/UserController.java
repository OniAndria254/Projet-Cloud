package itu.p16.crypto.controller;

import itu.p16.crypto.entity.Users;
import itu.p16.crypto.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersRepository userRepository;

    public UserController(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Endpoint pour récupérer tous les utilisateurs.
     * Pour des raisons de sécurité, il est conseillé d'utiliser un DTO qui n'inclut pas le mot de passe.
     *
     * @return la liste de tous les utilisateurs
     */
    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
