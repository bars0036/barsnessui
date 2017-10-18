package com.barsness;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = WelcomeView.VIEW_NAME)
public class WelcomeView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "welcome";

    public WelcomeView() {

        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Welcome");
        h1.addStyleName(ValoTheme.LABEL_H1);
        addComponent(h1);
    }
}
