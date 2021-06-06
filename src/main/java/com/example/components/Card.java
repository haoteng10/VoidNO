package com.example.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

public class Card extends Div {

    private String color = "";
    private int value = -1;
    private final boolean allowExpand;
    private CardType type = null;
    private int index;

    // 22% Action Cards, 70% Number Cards, 8% Wild Cards

    public Card(int index, boolean firstCard){
        prepareCard(firstCard);
        allowExpand = true;
        this.index = index;
        configure();
    }

    public Card(int index, boolean firstCard, boolean allowExpand){
        prepareCard(firstCard);
        this.allowExpand = allowExpand;
        this.index = index;
        configure();
    }

    public Card(int index, CardType type, String color, int num){
        this.index = index;
        this.type = type;
        this.color = color;
        this.value = num;
        this.allowExpand = true;

        configure();
    }

    private void prepareCard(boolean firstCard){
        double randType = Math.random();

        if (randType >= 0 && randType < 0.7 ){
            //Number Cards
            type = CardType.NUMBER;
            int randNum = (int)(Math.random() * 9) + 1; // 1 - 9 (inclusive)
            color = randColor();
            value = randNum;

        } else if (randType >= 0.7 && randType < 0.92){
            //Action Cards
            int randAction = (int) (Math.random() * 2);

            if (randAction == 0) {
                type = CardType.ACTION_PLUS_TWO;
            } else {
                type = CardType.ACTION_SKIP;
            }
            color = randColor();

        } else {
            //Go Wild Cards
            int randWild = (int) (Math.random() * 2);
            if (randWild == 0){
                type = CardType.WILD_COLOR;
            } else {
                type = CardType.WILD_PLUS_FOUR;
            }
            if (firstCard){
                color = randColor();
            } else {
                color = "Black";
            }
        }
    }

    private void configureColor(){
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

    private void configure(){

        configureColor();

        if (type == CardType.ACTION_PLUS_TWO) {
            Label label = new Label("+2");
            label.getStyle().set("color", "white");
            add(label);
        }

        if (type == CardType.ACTION_SKIP){
            Label label = new Label("Skip");
            label.getStyle().set("color", "white");
            add(label);
        }

        if (type == CardType.WILD_COLOR){
            Label label = new Label("RGBY");
            label.getStyle().set("color", "white");
            add(label);
        }

        if (type == CardType.WILD_PLUS_FOUR){
            Label label = new Label("+4");
            label.getStyle().set("color", "white");
            add(label);
        }

        if (type == CardType.NUMBER) {
            Label cardNum = new Label(value + "");
            cardNum.getStyle().set("color", "white");
            add(cardNum);
        }

        getClassNames().add("card");
        if (allowExpand) {
            getClassNames().add("card-expand");
        }
    }

    public static String randColor() {
        int rand = (int)(Math.random() * 4); // 0 - 3 (inclusive)
        if (rand == 0) return "Sinopia";
        if (rand == 1) return "Sapphire";
        if (rand == 2) return "Slimy Green";
        return "Safety Yellow";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        configureColor();
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

    public CardType getType() {
        return type;
    }

}
