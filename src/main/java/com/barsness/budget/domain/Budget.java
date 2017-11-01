package com.barsness.budget.domain;

import java.util.List;

public class Budget {

    private Long id;
    private String name;
    private String description;
    private List<BudgetCategory> budgetCategories;


    public Budget(Long id, String name, String description, List<BudgetCategory> budgetCategories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budgetCategories = budgetCategories;
    }

    public Budget() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(List<BudgetCategory> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }
}
