package com.barsness.budget.view;

import com.barsness.budget.modules.AssignTransactionModule;
import com.barsness.budget.modules.TransactionGridModule;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@SpringView(name=AssignTransactionView.VIEW_NAME)
public class AssignTransactionView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "assign-transaction";
    private TransactionGridModule grid;
    private AssignTransactionModule assign;


    public AssignTransactionView() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setMargin(true);

        Label header = new Label("Assign Transactions");
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);

        grid = new TransactionGridModule();
        addComponent(grid);
        assign = new AssignTransactionModule();
        assign.setVisible(false);
        addComponent(assign);

        grid.getTransactionGrid().setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.getTransactionGrid().asSingleSelect().addValueChangeListener(event2 -> {
            if (event2.getValue() == null) {
                assign.setVisible(false);
            } else {
                assign.setTransaction(event2.getValue());
                assign.setVisible(true);
            }
        });


    }


}
