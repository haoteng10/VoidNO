package com.example.components;

import com.vaadin.flow.component.html.Label;

public class BackCard extends Card {
    public BackCard(int index){
        super(index);

        getStyle().set("background-color", "gray");

        Label cardNum = (Label) getChildren().filter(component -> {
            return component instanceof Label;
        }).findFirst().get();

        cardNum.setVisible(false);
    }
}
