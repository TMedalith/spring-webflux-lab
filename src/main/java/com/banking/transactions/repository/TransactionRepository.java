package com.banking.transactions.repository;

import com.banking.transactions.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


//No se si esto es asi, voy a ver la clase
@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByAccountIdOrderByDate(String accountId);
}
