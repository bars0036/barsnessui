package com.barsness;

import com.barsness.budget.view.AssignTransactionView;
import com.barsness.budget.view.BudgetCategoryView;
import com.barsness.budget.view.TransactionView;
import com.barsness.core.ErrorView;
import com.barsness.core.WelcomeView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by matt.barsness on 10/11/17.
 */
@Theme("valo")
@Title("Barsnesss Family App")
@SpringUI
public class VaadinUI extends UI {

    BarsMenuLayout root = new BarsMenuLayout();
    ComponentContainer viewDisplay = root.getContentContainer();
    CssLayout menu = new CssLayout();
    CssLayout menuItemsLayout = new CssLayout();
    {
        menu.setId("mainMenu");
    }
    private Navigator navigator;

    @Autowired
    public VaadinUI(){

    }

    @Override
    protected void init(VaadinRequest request) {


        setContent(root);
        root.setWidth("100%");
        root.addMenu(buildMenu());
        addStyleName(ValoTheme.UI_WITH_MENU);
        navigator = new Navigator(this, viewDisplay);
        navigator.addView(TransactionView.VIEW_NAME, TransactionView.class);
        navigator.addView("error", ErrorView.class);
        navigator.addView("welcome", WelcomeView.class);
        navigator.addView(BudgetCategoryView.VIEW_NAME, BudgetCategoryView.class);
        navigator.addView(AssignTransactionView.VIEW_NAME, AssignTransactionView.class);

        String f = Page.getCurrent().getUriFragment();
        if (f == null || f.equals("")) {
            navigator.navigateTo("welcome");
        }

        navigator.setErrorView(ErrorView.class);

        Responsive.makeResponsive(this);

        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
               /* for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }*/
                menu.removeStyleName("valo-menu-visible");
            }
        });

    }

    private CssLayout buildMenu() {

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        Label title = new Label("<h3>Barsness <strong>Family App</strong></h3>",
                ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);
        MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");

        //TODO: Implement Security
        MenuBar.MenuItem settingsItem = settings.addItem("Matt Barsness",
                new ClassResource("/static/profile-pic-300px.jpg"),
                null);
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", null);
        menu.addComponent(settings);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);
        Label label = new Label("Budget", ContentMode.HTML);
        label.setPrimaryStyleName(ValoTheme.MENU_SUBTITLE);
        label.addStyleName(ValoTheme.LABEL_H4);
        label.setSizeUndefined();
        menuItemsLayout.addComponent(label);

        Button viewTransactionsButton = new Button("Transactions", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }
                event.getButton().addStyleName("selected");
                navigator.navigateTo(TransactionView.VIEW_NAME);
            }
        });
        viewTransactionsButton.setHtmlContentAllowed(true);
        viewTransactionsButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        viewTransactionsButton.setIcon(FontAwesome.DOLLAR);
        menuItemsLayout.addComponent(viewTransactionsButton);

        Button viewBudgetCategoryButton = new Button("Budget Categories", new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent event) {
                for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }
                event.getButton().addStyleName("selected");
                navigator.navigateTo(BudgetCategoryView.VIEW_NAME);
            }
        });
        viewBudgetCategoryButton.setHtmlContentAllowed(true);
        viewBudgetCategoryButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        viewBudgetCategoryButton.setIcon(FontAwesome.BARS);
        menuItemsLayout.addComponent(viewBudgetCategoryButton);

        Button viewAssignTransactionButton = new Button("Assign Transactions", new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent event) {
                for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }
                event.getButton().addStyleName("selected");
                navigator.navigateTo(AssignTransactionView.VIEW_NAME);
            }
        });
        viewAssignTransactionButton.setHtmlContentAllowed(true);
        viewAssignTransactionButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        viewAssignTransactionButton.setIcon(FontAwesome.ARROWS_H);
        menuItemsLayout.addComponent(viewAssignTransactionButton);


        return menu;


    }
}
