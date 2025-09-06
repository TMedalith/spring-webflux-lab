package com.banking.transactions.service.impl;

import com.banking.transactions.dto.AmountRequest;
import com.banking.transactions.dto.RequestTransfer;
import com.banking.transactions.model.Transaction;
import com.banking.transactions.model.TransactionType;
import com.banking.transactions.repository.TransactionRepository;
import com.banking.transactions.service.TransactionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<Transaction> deposit(String accountId, AmountRequest request) {
        //verificar que la cuenta exista, aqui conectar con otro microservicio


//actualizar el balance de la cuenta
        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .accountId(accountId)
                .date(LocalDate.now())
                .type(TransactionType.DEPOSIT)
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> withdrawal(String accountId, AmountRequest request) {

        //verificar que la cuenta exista, aqui conectar con otro microservicio

        //actualizar el balance de la cuenta
        Transaction transaction = Transaction.builder()
                .accountId(accountId)
                .date(LocalDate.now())
                .type(TransactionType.WITHDRAWAL)
                .amount(request.getAmount())
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> transfer(RequestTransfer request) {

        //verificar si ambos accounts existen

        // actualziar el monto en ambas cuentas, osea restarle a una y sumarle a otra
        // verificar de donde esta dsaliendo que haya saldo suficiente

        Transaction transaction = Transaction.builder()
                .accountId(request.getSourceAccountId())
                .externalAccountId(request.getDestinationAccountId())
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .date(LocalDate.now()).build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Flux<Transaction> history(String accountId) {
        return transactionRepository.findByAccountIdOrderByDate(accountId);
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
