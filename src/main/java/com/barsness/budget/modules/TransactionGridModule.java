package com.barsness.budget.modules;

import com.barsness.budget.domain.Transaction;
import com.barsness.budget.rest.BudgetRestClient;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransactionGridModule extends VerticalLayout {

    BudgetRestClient restClient = new BudgetRestClient();

    private DateField startDateField;
    private DateField endDateField;
    private Button searchButton;
    private Grid<Transaction> transactionGrid;

    public TransactionGridModule() {
        createTransactionGrid();

    }

    public void createTransactionGrid() {
        setMargin(true);
        setSpacing(true);

        setMargin(new MarginInfo(false, false, false, false));

        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        row.setSpacing(true);
        addComponent(row);

        startDateField = new DateField("Start Date");
        startDateField.setValue(LocalDate.now().minusDays(30));
        row.addComponent(startDateField );

        endDateField = new DateField("End Date");
        endDateField.setValue(LocalDate.now());
        row.addComponent(endDateField);

        searchButton = new Button("Search", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                loadGrid();
            }
        });
        row.addComponent(searchButton);
        row.setComponentAlignment(searchButton, Alignment.BOTTOM_LEFT);

        transactionGrid = new Grid();
        transactionGrid.setWidth("850");
        transactionGrid.addColumn(Transaction::getId).setCaption("Id").setWidth(80);
        transactionGrid.addColumn(Transaction::getDescription).setCaption("Description");
        transactionGrid.addColumn(Transaction::getTransactionDateString).setCaption("Transaction Date");
        transactionGrid.addColumn(Transaction::getValue).setCaption("Value");

        //transactionGrid.addColumn(Transaction::isAssigned).setCaption("Category Assigned");
        transactionGrid.addComponentColumn(transaction -> {return new CheckBox("", transaction.isAssigned());}).setCaption("Category Assigned");
        //transactionGrid.setStyleGenerator(cell -> "Category Assigned".equals("Category Assigned"));

        addComponent(transactionGrid);
        loadGrid();

    }

    public Grid<Transaction> getTransactionGrid() {
        return transactionGrid;
    }

    public void setTransactionGrid(Grid<Transaction> transactionGrid) {
        this.transactionGrid = transactionGrid;
    }

    private void loadGrid() {
        transactionGrid.setItems(restClient.getTransactionsBetween(LocalDateTime.of(startDateField.getValue(), LocalTime.of(0,0)), LocalDateTime.of(endDateField.getValue(), LocalTime.of(0,0))));
    }
}
