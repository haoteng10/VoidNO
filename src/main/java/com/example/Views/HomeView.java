package com.example.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
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
        button.addClickListener(e -> UI.getCurrent().navigate(GameView.class));

        Div buttonDiv = new Div(button);
        buttonDiv.addClassName("button-div");

        add(h1, h2, buttonDiv);
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        initialPageSettings.addLink("stylesheet","https://fonts.googleapis.com/css2?family=Luckiest+Guy&display=swap");
    }
}
