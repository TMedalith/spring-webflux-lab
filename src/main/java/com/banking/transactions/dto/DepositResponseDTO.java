package com.banking.transactions.dto;

import java.time.LocalDate;

import com.banking.transactions.model.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositResponseDTO {
    private String id;
    private TransactionType type;
    private Double amount;
    private LocalDate date;
    private String sourceAccountId;
}
