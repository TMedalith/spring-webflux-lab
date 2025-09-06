package com.banking.transactions.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@Document("transactions")
public class Transaction {

    @Id
    private String id;

    private TransactionType type;

    private Double amount;

    private LocalDate date;

    private String accountId;

    private String externalAccountId;

}
