package com.example.Views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@CssImport("./styles/home.css")
@Route("")
public class HomeView extends VerticalLayout implements PageConfigurator {
    public HomeView(){
        setHeight("100vh");
        setWidth("100vw");

        H1 h1 = new H1("VoidNO");
        H2 h2 = new H2("Made by VoidGames for educational purposes only");

        Button button = new Button();
        button.setText("Play!");
        button.setHeight("100px");
        button.setWidth("300px");
        button.addClickListener(e -> UI.getCurrent().navigate(GameView.class));

        Button button2 = new Button();
        button2.setText("Rules");
        button2.setHeight("100px");
        button2.setWidth("300px");
        button2.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                Dialog dialog = new Dialog();

                VerticalLayout vl = new VerticalLayout();

                H1 h1 = new H1("Rules of VoidNo");

                vl.add(h1);

                dialog.add(vl);
                dialog.open();
            }
        });

        Div buttonDiv = new Div();
        buttonDiv.add(button, button2);
        buttonDiv.addClassName("button-div");

        add(h1, h2, buttonDiv);
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        initialPageSettings.addLink("stylesheet","https://fonts.googleapis.com/css2?family=Luckiest+Guy&display=swap");
    }
}
