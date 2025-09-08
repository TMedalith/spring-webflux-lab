package com.banking.transactions.dto;

import lombok.Data;


@Data
public class RequestTransfer {
    private String sourceAccountId;
    private String destinationAccountId;
    private Double amount;
}
