package com.banking.transactions.mapper;

import com.banking.transactions.dto.TransactionResponse;
import com.banking.transactions.model.Transaction;

public final class TransactionMapper {

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .sourceAccountId(transaction.getSourceAccountId())
                .destinationAccountId(transaction.getDestinationAccountId()) // Será null para DEPOSIT/WITHDRAWAL
                .build();
    }

}
