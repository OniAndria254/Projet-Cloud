package itu.p16.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import itu.p16.crypto.service.AnalyseService;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, Object>> analyseTransactions(
            @RequestParam Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax) {
        Map<String, Object> result = analyseService.getAnalyseTransactions(idCrypto, dateMin, dateMax);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/commissions")
    public ResponseEntity<Map<String, Object>> analyseCommissions(
            @RequestParam Long idCrypto,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateMax) {
        Map<String, Object> result = analyseService.getAnalyseCommissions(idCrypto, dateMin, dateMax);
        return ResponseEntity.ok(result);
    }
}