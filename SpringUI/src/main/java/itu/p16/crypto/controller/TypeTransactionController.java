package itu.p16.crypto.controller;

import itu.p16.crypto.entity.TypeTransaction;
import itu.p16.crypto.repository.TypeTransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeTransaction")
public class TypeTransactionController {

    private final TypeTransactionRepository typeTransactions;

    public TypeTransactionController(TypeTransactionRepository typeTransactions) {
        this.typeTransactions = typeTransactions;
    }
    
    @GetMapping("/all")
    public List<TypeTransaction> getAllTypeTransactions() {
        return typeTransactions.findAll();
    }
}
