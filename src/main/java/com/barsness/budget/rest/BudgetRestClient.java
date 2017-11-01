package com.barsness.budget.rest;

import com.barsness.budget.domain.Budget;
import com.barsness.budget.domain.BudgetTransaction;
import com.barsness.budget.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BudgetRestClient {

    private static final Logger logger = LoggerFactory.getLogger(BudgetRestClient.class);

    //@Value("${com.barsness.budget.baseurl}")
    private String budgetUrl = "http://localhost:8081";

    private RestTemplate restTemplate = new RestTemplate();

    public BudgetRestClient() {
    }

    public Transaction[] getAllTransactions(){

        ResponseEntity<Transaction[]> transactionsResponse = restTemplate.getForEntity(this.budgetUrl+ "/transaction/", Transaction[].class);
        Transaction[] transactions = transactionsResponse.getBody();
        return transactions;
    }

    public Transaction[] getTransactionsBetween(LocalDateTime startDate, LocalDateTime endDate){
        ResponseEntity<Transaction[]> transactionsResponse = restTemplate.getForEntity(this.budgetUrl+ "/transaction/find-by-date?startDate={startDate}&endDate={endDate}", Transaction[].class, startDate, endDate);
        Transaction[] transactions = transactionsResponse.getBody();
        return transactions;
    }

    public Budget getBudget(Long id){

        ResponseEntity<Budget> budgetResponse = restTemplate.getForEntity(this.budgetUrl + "/budget/{id}", Budget.class, id);
        return budgetResponse.getBody();
    }

    public void assignTransaction(Long budgetCategoryId, Long transactionId, Long personId, BigDecimal value){
        ResponseEntity<BudgetTransaction> forEntity = restTemplate.exchange(this.budgetUrl + "/budget/category/{id}/assign-transaction/?transactionId={transactionId}&personId={personId}&value={value}", HttpMethod.PUT, null, BudgetTransaction.class, budgetCategoryId, transactionId, personId, value);
        logger.info("Transaction Assigned: " + forEntity.getBody().toString());
    }

}
