package com.barsness.budget.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetTransaction {

    private Long id;
    private Long budgetCategoryId;
    private Long transactionId;
    private Long personId;
    private LocalDateTime dateAssigned;
    private BigDecimal value;
    private boolean autoAssigned;

    public BudgetTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Long budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDateTime getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(LocalDateTime dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isAutoAssigned() {
        return autoAssigned;
    }

    public void setAutoAssigned(boolean autoAssigned) {
        this.autoAssigned = autoAssigned;
    }
}
