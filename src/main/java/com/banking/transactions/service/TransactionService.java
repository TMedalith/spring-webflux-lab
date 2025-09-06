package com.banking.transactions.service;

import com.banking.transactions.dto.AmountRequest;
import com.banking.transactions.dto.RequestTransfer;
import com.banking.transactions.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TransactionService {

     Mono<Transaction> deposit(String accountId, AmountRequest request);
     Mono<Transaction> withdrawal(String accountId,AmountRequest request);
     Mono<Transaction> transfer(RequestTransfer request);
     Flux<Transaction> history(String accountId);

    Flux<Transaction> getAllTransactions();
}
