package com.example.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

public class Card extends Div {

    private final String color;
    private final int value;
    private int index;
    private boolean allowExpand;

    public Card(int index){
        int randNum = (int)(Math.random() * 9) + 1; // 1 - 9 (inclusive)

        color = randColor();
        value = randNum;
        allowExpand = true;
        this.index = index;

        configure();
    }

    public Card(int index, boolean allowExpand){
        int randNum = (int)(Math.random() * 9) + 1; // 1 - 9 (inclusive)

        color = randColor();
        value = randNum;

        this.index = index;
        this.allowExpand = allowExpand;

        configure();
    }

    public Card(int index, String color, int num){
        this.index = index;
        this.color = color;
        this.value = num;
        this.allowExpand = true;

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

        switch (color) {
            case "Sapphire":
                getStyle().set("background-color", "#0956BF");
                break;
            case "Sinopia":
                getStyle().set("background-color", "#D72600");
                break;
            case "Slimy Green":
                getStyle().set("background-color", "#379711");
                break;
            case "Safety Yellow":
                getStyle().set("background-color", "#ECD407");
                break;
            case "Black":
                getStyle().set("background-color", "#000000");
        }
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index){
        this.index = index;
    }
}
