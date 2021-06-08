package com.example.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Card extends Div {

    private String color = "";
    private int value = -1;
    private final boolean allowExpand;
    private CardType type = null;
    private int index;
    private boolean selectedColor = false;

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
            label.getStyle().set("font-size", "2em");
            add(label);
        }

        if (type == CardType.ACTION_SKIP){
            Label label = new Label("Skip");
            label.getStyle().set("color", "white");
            label.getStyle().set("font-size", "2em");
            add(label);
        }

        if (type == CardType.WILD_COLOR){
            Label label = new Label("Go Wild!");
            label.getStyle().set("color", "white");
            label.getStyle().set("font-size", "2em");
            add(label);
            colorSelector();
        }

        if (type == CardType.WILD_PLUS_FOUR){
            Label label = new Label("+4");
            label.getStyle().set("color", "white");
            label.getStyle().set("font-size", "2em");
            add(label);
            colorSelector();
        }

        if (type == CardType.NUMBER) {
            Label cardNum = new Label(value + "");
            cardNum.getStyle().set("color", "white");
            cardNum.getStyle().set("font-size", "2em");
            add(cardNum);
        }

        getClassNames().add("card");
        if (allowExpand) {
            getClassNames().add("card-expand");
        }
    }

    private void colorSelector(){
        if (!color.equals("Black")) return;

        HorizontalLayout row1 = new HorizontalLayout();
        HorizontalLayout row2 = new HorizontalLayout();

        String size = "30px";

        Div redSquare = new Div();
        redSquare.setWidth(size);
        redSquare.setHeight(size);
        redSquare.getStyle().set("background-color", "#D72600");
        redSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Sinopia";
                configureColor();
                selectedColor = true;
            }
        });

        Div blueSquare = new Div();
        blueSquare.setWidth(size);
        blueSquare.setHeight(size);
        blueSquare.getStyle().set("background-color", "#0956BF");
        blueSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Sapphire";
                configureColor();
                selectedColor = true;
            }
        });

        Div greenSquare = new Div();
        greenSquare.setWidth(size);
        greenSquare.setHeight(size);
        greenSquare.getStyle().set("background-color", "#379711");
        greenSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Slimy Green";
                configureColor();
                selectedColor = true;
            }
        });

        Div yellowSquare = new Div();
        yellowSquare.setWidth(size);
        yellowSquare.setHeight(size);
        yellowSquare.getStyle().set("background-color", "#ECD407");
        yellowSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Safety Yellow";
                configureColor();
                selectedColor = true;
            }
        });

        row1.add(redSquare, blueSquare);
        row1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        row2.add(greenSquare, yellowSquare);
        row2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(row1);
        add(row2);

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

    public boolean isSelectedColor(){
        return selectedColor;
    }

}
