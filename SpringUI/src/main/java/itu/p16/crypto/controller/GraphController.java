package itu.p16.crypto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphic")
public class GraphController {

    @GetMapping("/graphe")
    public String graphique() {
        return "page/graphique";
    }
}
