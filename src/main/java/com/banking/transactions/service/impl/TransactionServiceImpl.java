package com.banking.transactions.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.banking.transactions.dto.AmountRequest;
import com.banking.transactions.dto.DepositResponseDTO;
import com.banking.transactions.dto.RequestTransfer;
import com.banking.transactions.dto.TransferResponseDTO;
import com.banking.transactions.dto.WithdrawalResponseDTO;
import com.banking.transactions.mapper.TransactionMapper;
import com.banking.transactions.model.Transaction;
import com.banking.transactions.model.TransactionType;
import com.banking.transactions.repository.TransactionRepository;
import com.banking.transactions.service.TransactionService;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient webClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WebClient webClient){
        this.transactionRepository = transactionRepository;
        this.webClient = webClient;
    }



    @Override
    public Mono<DepositResponseDTO> deposit(String accountId, AmountRequest request) {

        webClient.get()
                .uri("/api/v1/accounts/{id}/depositar", accountId)
        //verificar que la cuenta exista, aqui conectar con otro microservicio


//actualizar el balance de la cuenta
        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .sourceAccountId(accountId)
                .date(LocalDate.now())
                .type(TransactionType.DEPOSIT)
                .build();
        return transactionRepository.save(transaction)
                .map(TransactionMapper::toDepositResponseDTO);
    }

    @Override
    public Mono<WithdrawalResponseDTO> withdrawal(String accountId, AmountRequest request) {

        //verificar que la cuenta exista, aqui conectar con otro microservicio

        //actualizar el balance de la cuenta
        Transaction transaction = Transaction.builder()
                .sourceAccountId(accountId)
                .date(LocalDate.now())
                .type(TransactionType.WITHDRAWAL)
                .amount(request.getAmount())
                .build();

        return transactionRepository.save(transaction)
                .map(TransactionMapper::toWithdrawalResponseDTO);
    }

    @Override
    public Mono<TransferResponseDTO> transfer(RequestTransfer request) {

        //verificar si ambos accounts existen

        // actualziar el monto en ambas cuentas, osea restarle a una y sumarle a otra
        // verificar de donde esta dsaliendo que haya saldo suficiente

        Transaction transaction = Transaction.builder()
                .sourceAccountId(request.getSourceAccountId())
                .destinationAccountId(request.getDestinationAccountId())
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .date(LocalDate.now()).build();
        return transactionRepository.save(transaction)
                .map(TransactionMapper::toTransferResponseDTO);
    }

    @Override
    public Flux<Object> history(String accountId) {
        return transactionRepository.findBySourceAccountIdOrderByDate(accountId)
                .map(TransactionMapper::toAppropriateResponseDTO);
    }

    @Override
    public Flux<Object> getAllTransactions() {
        return transactionRepository.findAll()
                .map(TransactionMapper::toAppropriateResponseDTO);
    }
}
