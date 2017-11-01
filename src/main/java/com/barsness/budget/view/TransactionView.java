package com.barsness.budget.view;

import com.barsness.budget.domain.Transaction;
import com.barsness.budget.modules.TransactionGridModule;
import com.barsness.budget.rest.BudgetRestClient;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@SpringView(name=TransactionView.VIEW_NAME)
public class TransactionView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "transaction";


    public TransactionView() {


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //setMargin(true);
        //setSpacing(true);

        Label h1 = new Label("Transactions List");
        h1.addStyleName(ValoTheme.LABEL_H1);
        addComponent(h1);

        addComponent(new TransactionGridModule());



    }


}
