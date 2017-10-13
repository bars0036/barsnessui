package com.barsness;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by matt.barsness on 10/11/17.
 */
@SpringUI
public class VaadinUI extends UI {

    private RestTemplate restTemplate = new RestTemplate();

    private Grid<Transaction> transactionGrid;

    @Autowired
    public VaadinUI(){
        this.transactionGrid = new Grid();
    }

    @Override
    protected void init(VaadinRequest request) {

        Panel panel = new Panel("This is a Panel");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.addComponent(new Label("Transactions"));

        ResponseEntity<Transaction[]> transactionsResponse = restTemplate.getForEntity("http://localhost:8081/transaction/", Transaction[].class);
        Transaction[] transactions = transactionsResponse.getBody();
        //transactionGrid.setColumns("id", "transactionDate", "description", "value");

        transactionGrid.setItems(transactions);
        transactionGrid.addColumn(Transaction::getId).setCaption("Id");
        transactionGrid.addColumn(Transaction::getDescription).setCaption("Description");
        transactionGrid.addColumn(Transaction::getTransactionDate).setCaption("Transaction Date");
        transactionGrid.addColumn(Transaction::getValue).setCaption("Value");
        panelContent.addComponents(transactionGrid);

        panel.setContent(panelContent);



        setContent(panel);
    }
}
