package com.barsness;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringView(name=TransactionView.VIEW_NAME)
public class TransactionView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "transaction";

    private RestTemplate restTemplate = new RestTemplate();

    private Grid<Transaction> transactionGrid;

    public TransactionView() {
        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Transactions List");
        h1.addStyleName(ValoTheme.LABEL_H1);
        addComponent(h1);

        ResponseEntity<Transaction[]> transactionsResponse = restTemplate.getForEntity("http://localhost:8081/transaction/", Transaction[].class);
        Transaction[] transactions = transactionsResponse.getBody();
        //transactionGrid.setColumns("id", "transactionDate", "description", "value");
        transactionGrid = new Grid();
        transactionGrid.setWidth("700px");
        transactionGrid.setItems(transactions);
        transactionGrid.addColumn(Transaction::getId).setCaption("Id").setWidth(80);
        transactionGrid.addColumn(Transaction::getDescription).setCaption("Description");
        transactionGrid.addColumn(Transaction::getTransactionDate).setCaption("Transaction Date");
        transactionGrid.addColumn(Transaction::getValue).setCaption("Value");
        addComponent(transactionGrid);

    }
}
