package com.barsness.budget.domain;

import java.util.List;

public class BudgetCategory {

    private Long id;
    private String categoryName;
    private String categoryDescription;
    private List<BudgetCategory> budgetCategories;
    private String displayName;

    public BudgetCategory() {
    }

    public BudgetCategory(Long id, String categoryName, String categoryDescription, List<BudgetCategory> budgetCategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.budgetCategories = budgetCategories;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(List<BudgetCategory> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }
}
