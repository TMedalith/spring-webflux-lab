package com.banking.transactions.mapper;

import com.banking.transactions.dto.DepositResponseDTO;
import com.banking.transactions.dto.TransferResponseDTO;
import com.banking.transactions.dto.WithdrawalResponseDTO;
import com.banking.transactions.model.Transaction;

public final class TransactionMapper {

    public static DepositResponseDTO toDepositResponseDTO(Transaction transaction) {
        return DepositResponseDTO.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .sourceAccountId(transaction.getSourceAccountId())
                .build();
    }

    public static WithdrawalResponseDTO toWithdrawalResponseDTO(Transaction transaction) {
        return WithdrawalResponseDTO.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .sourceAccountId(transaction.getSourceAccountId())
                .build();
    }

    public static TransferResponseDTO toTransferResponseDTO(Transaction transaction) {
        return TransferResponseDTO.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .sourceAccountId(transaction.getSourceAccountId())
                .destinationAccountId(transaction.getDestinationAccountId())
                .build();
    }

    public static Object toAppropriateResponseDTO(Transaction transaction) {
        return switch (transaction.getType()) {
            case DEPOSIT -> toDepositResponseDTO(transaction);
            case WITHDRAWAL -> toWithdrawalResponseDTO(transaction);
            case TRANSFER -> toTransferResponseDTO(transaction);
        };
    }
}
