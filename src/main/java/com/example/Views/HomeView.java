package com.example.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {
    public HomeView(){
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button button = new Button();
        button.setText("Play!");
        button.addClickListener(e -> UI.getCurrent().navigate(GameView.class));

        horizontalLayout.add(button);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(horizontalLayout);
    }
}
