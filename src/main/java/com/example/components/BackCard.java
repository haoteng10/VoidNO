package com.example.components;

import com.vaadin.flow.component.html.Label;

public class BackCard extends Card {
    public BackCard(int index, boolean firstCard){
        super(index, firstCard);

        getStyle().set("background-color", "gray");

        Label cardNum = (Label) getChildren().filter(component -> {
            return component instanceof Label;
        }).findFirst().get();

        cardNum.setVisible(false);
    }

    public BackCard(int index, CardType type, String color, int value) {
        super(index, type, color, value);

        getStyle().set("background-color", "gray");

        Label cardNum = (Label) getChildren().filter(component -> {
            return component instanceof Label;
        }).findFirst().get();

        cardNum.setVisible(false);
    }
}
