package com.barsness.core;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "error";

    public ErrorView() {


        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Transactions List");
        h1.addStyleName(ValoTheme.LABEL_H1);
        addComponent(h1);
    }
}
