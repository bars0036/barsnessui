package com.barsness.budget.view;

import com.barsness.budget.domain.Budget;
import com.barsness.budget.domain.BudgetCategory;
import com.barsness.budget.rest.BudgetRestClient;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SpringView(name=BudgetCategoryView.VIEW_NAME)
public class BudgetCategoryView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "budget-category";

    private static final Logger logger = LoggerFactory.getLogger(BudgetCategoryView.class);

    BudgetRestClient restClient = new BudgetRestClient();

    private Tree tree;
    private TreeData<String> treeData;
    private TreeDataProvider<String> tdp;

    public BudgetCategoryView() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        setMargin(true);

        Label h1 = new Label("Budget Categories");
        h1.addStyleName(ValoTheme.LABEL_H1);
        addComponent(h1);

        Budget budget = restClient.getBudget(new Long(5909));

        tree = new Tree();

        treeData = new TreeData<>();
        tree.setSelectionMode(Grid.SelectionMode.SINGLE);
        buildTree(budget.getBudgetCategories(), null);
        tdp = new TreeDataProvider<>(treeData);
        tree.setDataProvider(tdp);
        //tree.addExpandListener(event2 -> tree.expand(event2.getExpandedItem()));
        //tree.addCollapseListener(event2 -> log("Item collapsed: " + event2.getCollapsedItem()));
        addComponent(tree);

    }

    private void log(String msg){
        logger.info(msg);
    }

    private void buildTree(List<BudgetCategory> categories, String parent){
        for(BudgetCategory category:categories){
            this.treeData.addItem(parent, category.getCategoryName() + " - " + category.getId());
            if(category.getBudgetCategories() != null){
                buildTree(category.getBudgetCategories(), category.getCategoryName() + " - " + category.getId());
            }
        }
    }
}
