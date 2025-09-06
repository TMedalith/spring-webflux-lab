package com.banking.transactions.controller;


import com.banking.transactions.dto.AmountRequest;
import com.banking.transactions.dto.RequestTransfer;
import com.banking.transactions.model.Transaction;
import com.banking.transactions.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}")
    public Flux<Transaction> history(@PathVariable String accountId) {
        return transactionService.history(accountId);
    }

    @GetMapping
    public Flux<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping("/{accountId}/deposit")
    public Mono<Transaction> deposit(@PathVariable String accountId, @RequestBody AmountRequest request){
        return transactionService.deposit(accountId, request);
    }

    @PostMapping("/{accountId}/withdrawal")
    public  Mono<Transaction> withdrawal(@PathVariable String accountId, @RequestBody AmountRequest request){
        return transactionService.withdrawal(accountId, request);
    }



    @PostMapping("/transfer")
    public Mono<Transaction> transfer(@RequestBody RequestTransfer request){
        return transactionService.transfer(request);
    }
}
