package com.example.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;

public class BackCard extends Card {
    public BackCard(){
        getStyle().set("background-color", "gray");

        Label cardNum = (Label) getChildren().filter(component -> {
           if (component instanceof Label) {
               return true;
           }
           return false;
        }).findFirst().get();

        cardNum.setVisible(false);
    }
}
