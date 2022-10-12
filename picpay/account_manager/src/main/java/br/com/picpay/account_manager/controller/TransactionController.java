package br.com.picpay.account_manager.controller;

import br.com.picpay.account_manager.model.dto.TransactionDTO;
import br.com.picpay.account_manager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    private ResponseEntity getTransaction() {
        return ResponseEntity.ok("Transaction");
    }

    @PostMapping
    private ResponseEntity makeTransaction(@RequestBody TransactionDTO request) {
        transactionService.makeTransaction(request);
        return ResponseEntity.ok().build();
    }
}
