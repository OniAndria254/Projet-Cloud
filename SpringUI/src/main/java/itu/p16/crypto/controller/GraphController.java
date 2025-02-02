package itu.p16.crypto.controller;

import itu.p16.crypto.exception.NoUserLoggedException;
import itu.p16.crypto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphic")
public class GraphController {
    @Autowired
    private AuthService authService;

    @GetMapping("/graphe")
    public String graphique() throws NoUserLoggedException {
        authService.requireUser();
        return "page/graphique";
    }
}
