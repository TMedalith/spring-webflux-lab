package com.banking.transactions.service;

import com.banking.transactions.dto.AmountRequest;
import com.banking.transactions.dto.DepositResponseDTO;
import com.banking.transactions.dto.RequestTransfer;
import com.banking.transactions.dto.TransferResponseDTO;
import com.banking.transactions.dto.WithdrawalResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

     Mono<DepositResponseDTO> deposit(String accountId, AmountRequest request);
     Mono<WithdrawalResponseDTO> withdrawal(String accountId,AmountRequest request);
     Mono<TransferResponseDTO> transfer(RequestTransfer request);
     Flux<Object> history(String accountId);

    Flux<Object> getAllTransactions();
}
