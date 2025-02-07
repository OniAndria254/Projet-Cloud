package itu.p16.crypto.controller;

import itu.p16.crypto.service.TransactionService;
import itu.p16.crypto.entity.TransactionSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/transaction2")
public class TransactionController2 {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/analysis")
    public String showAnalysisPage(Model model) {
        List<TransactionSummaryDTO> summaries = transactionService.getTransactionSummary();
        model.addAttribute("summaries", summaries);
        return "admin/analysis";
    }

    @PostMapping("/analysis/results")
    public String filterResults(@RequestParam(name = "dateMax") String dateMax, Model model) {
        List<TransactionSummaryDTO> filteredSummaries = transactionService.getTransactionSummaryFilteredByDate(dateMax);
        model.addAttribute("summaries", filteredSummaries);
        return "admin/analysis";
    }
}
