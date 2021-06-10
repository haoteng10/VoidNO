package com.example.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class BackCard extends Card {
    public BackCard(int index, boolean firstCard){
        super(index, firstCard);

        getStyle().set("background-color", "#E0791F");

        Label cardNum = (Label) getChildren().filter(component -> {
            return component instanceof Label;
        }).findFirst().get();

        getChildren().forEach(component -> {
            if (component instanceof HorizontalLayout) {
                component.setVisible(false);
            }
        });

        cardNum.setVisible(false);

        Div logo = new Div(new Text("VoidNo"));
        logo.getStyle().set("color", "white");
        logo.getStyle().set("font-size", "1.5em");
        add(logo);
    }

    public BackCard(int index, CardType type, String color, int value) {
        super(index, type, color, value);

        getStyle().set("background-color", "#E0791F");

        Label cardNum = (Label) getChildren().filter(component -> {
            return component instanceof Label;
        }).findFirst().get();

        getChildren().forEach(component -> {
            if (component instanceof HorizontalLayout) {
                component.setVisible(false);
            }
        });

        cardNum.setVisible(false);

        Div logo = new Div(new Text("VoidNo"));
        logo.getStyle().set("color", "white");
        logo.getStyle().set("font-size", "2em");
        add(logo);
    }
}
