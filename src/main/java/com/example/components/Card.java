package com.example.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

public class Card extends Div {

    private String color;
    private int value;
    private boolean allowExpand;

    public Card(){
        int randNum = (int)(Math.random() * 9) + 1; // 1 - 9 (inclusive)
        String randColor = randColor();

        color = randColor;
        value = randNum;
        allowExpand = true;

        configure();
    }

    public Card(boolean allowExpand){
        int randNum = (int)(Math.random() * 9) + 1; // 1 - 9 (inclusive)
        String randColor = randColor();

        color = randColor;
        value = randNum;
        this.allowExpand = allowExpand;

        configure();
    }

    public Card(String color, int num){
        this.color = color;
        this.value = num;

        configure();
    }

    private void configure(){
        configureCardColor();
        configureCardNum();
        configureCardStyle();
    }

    private static String randColor() {
        int rand = (int)(Math.random() * 4); // 0 - 3 (inclusive)
        if (rand == 0) return "Sinopia";
        if (rand == 1) return "Sapphire";
        if (rand == 2) return "Slimy Green";
        return "Safety Yellow";
    }

    private void configureCardStyle(){
        getClassNames().add("card");

        if (allowExpand) {
            getClassNames().add("card-expand");
        }
    }

    private void configureCardNum() {
        Label cardNum = new Label(value + "");
        cardNum.getStyle().set("color", "white");
        add(cardNum);
    }

    private void configureCardColor() {

        if (color.equals("Sapphire")){
            getStyle().set("background-color", "#0956BF");
        } else if (color.equals("Sinopia")){
            getStyle().set("background-color", "#D72600");
        } else if (color.equals("Slimy Green")) {
            getStyle().set("background-color", "#379711");
        } else if (color.equals("Safety Yellow")){
            getStyle().set("background-color", " #ECD407");
        }
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
