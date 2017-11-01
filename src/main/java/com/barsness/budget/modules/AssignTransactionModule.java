package com.barsness.budget.modules;

import com.barsness.budget.domain.Budget;
import com.barsness.budget.domain.BudgetCategory;
import com.barsness.budget.domain.Transaction;
import com.barsness.budget.rest.BudgetRestClient;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

public class AssignTransactionModule extends VerticalLayout {

    private TextField date;
    private TextField desc;
    private TextField value;
    private ComboBox<BudgetCategory> combo;
    private Transaction transaction;
    BudgetRestClient restClient = new BudgetRestClient();

    public AssignTransactionModule(){
        createModule();
    }

    private void createModule(){
        setMargin(false);
        Label title = new Label("Assign Category");
        title.addStyleName(ValoTheme.LABEL_H3);
        addComponent(title);

        HorizontalLayout tran = new HorizontalLayout();

        date = new TextField("Date");
        date.setReadOnly(true);
        tran.addComponent(date);

        desc = new TextField("Description");
        desc.setReadOnly(true);
        tran.addComponent(desc);

        value = new TextField("Value");
        value.setReadOnly(true);
        tran.addComponent(value);

        addComponent(tran);

        combo = new ComboBox<>("Budget Category");
        List<BudgetCategory> budgetCategoryList = new ArrayList<>();
        //TODO Determine Budget ID to use.
        Budget budget = restClient.getBudget(new Long(5909));
        buildBudgetCategories(budgetCategoryList, budget.getBudgetCategories(), 0);
        combo.setItems(budgetCategoryList);
        combo.setItemCaptionGenerator(BudgetCategory::getDisplayName);
        combo.addValueChangeListener(catEvent -> {
            //TODO: Set Person ID
            restClient.assignTransaction(catEvent.getValue().getId(), transaction.getId(), new Long(1), transaction.getValue());
        });
        tran.addComponent(combo);


    }

    private void buildBudgetCategories(List<BudgetCategory> budgetCategoryList, List<BudgetCategory> budgetCategories, int level) {
        for(BudgetCategory bc:budgetCategories){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<level; i++ ){
                sb.append("   ");
                if(level-1 == i){
                    sb.append("-");
                }
            }
            sb.append(bc.getCategoryName());
            bc.setDisplayName(sb.toString());
            budgetCategoryList.add(bc);
            if(bc.getBudgetCategories() != null){
                buildBudgetCategories(budgetCategoryList, bc.getBudgetCategories(), level+1);
            }
        }
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.populateForm();
    }

    private void populateForm() {
        date.setValue(transaction.getTransactionDateString());
        desc.setValue(transaction.getDescription());
        value.setValue(transaction.getValue().toString());
    }


}
