package com.example.components;

import com.example.Game;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ColorSelector {

    private String color = "";
//    private boolean colorChanged = false;

//    public CompletableFuture<String> select() {
//        CompletableFuture<String> selectedColor = new CompletableFuture<>();
//
//        Executors.newCachedThreadPool().submit(() -> {
//            Dialog dialog = new Dialog();
//            dialog.setWidth("600px");
//            dialog.setHeight("600px");
//            dialog.setCloseOnEsc(false);
//            dialog.setCloseOnOutsideClick(false);
//
//            HorizontalLayout row1 = new HorizontalLayout();
//            HorizontalLayout row2 = new HorizontalLayout();
//
//            Div redSquare = new Div();
//            redSquare.setWidth("300px");
//            redSquare.setHeight("300px");
//            redSquare.getStyle().set("background-color", "#D72600");
//            redSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
//                @Override
//                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
//                    color = "Sinopia";
//                    dialog.close();
//                    selectedColor.complete("Sinopia");
//                }
//            });
//
//            Div blueSquare = new Div();
//            blueSquare.setWidth("300px");
//            blueSquare.setHeight("300px");
//            blueSquare.getStyle().set("background-color", "#0956BF");
//            blueSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
//                @Override
//                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
//                    color = "Sapphire";
//                    dialog.close();
//                    selectedColor.complete("Sapphire");
//                }
//            });
//
//            Div greenSquare = new Div();
//            greenSquare.setWidth("300px");
//            greenSquare.setHeight("300px");
//            greenSquare.getStyle().set("background-color", "#379711");
//            greenSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
//                @Override
//                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
//                    color = "Slimy Green";
//                    dialog.close();
//                    selectedColor.complete("Slimy Green");
//                }
//            });
//
//            Div yellowSquare = new Div();
//            yellowSquare.setWidth("300px");
//            yellowSquare.setHeight("300px");
//            yellowSquare.getStyle().set("background-color", "#ECD407");
//            yellowSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
//                @Override
//                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
//                    color = "Safety Yellow";
//                    dialog.close();
//                    selectedColor.complete("Safety Yellow");
//                }
//            });
//
//            row1.add(redSquare, blueSquare);
//            row2.add(greenSquare, yellowSquare);
//
//            dialog.add(row1,row2);
//            dialog.open();
//
//            return null;
//        });
//
//        return selectedColor;
//    }

    public void selectSync(){
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setHeight("600px");
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        HorizontalLayout row1 = new HorizontalLayout();
        HorizontalLayout row2 = new HorizontalLayout();

        Div redSquare = new Div();
        redSquare.setWidth("300px");
        redSquare.setHeight("300px");
        redSquare.getStyle().set("background-color", "#D72600");
        redSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Sinopia";
                dialog.close();
                Game.modifyLastPlayedCardColor(color);
                Game.dialogOpened = false;
            }
        });

        Div blueSquare = new Div();
        blueSquare.setWidth("300px");
        blueSquare.setHeight("300px");
        blueSquare.getStyle().set("background-color", "#0956BF");
        blueSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Sapphire";
                dialog.close();
            Game.modifyLastPlayedCardColor(color);
            Game.dialogOpened = false;
            }
        });

        Div greenSquare = new Div();
        greenSquare.setWidth("300px");
        greenSquare.setHeight("300px");
        greenSquare.getStyle().set("background-color", "#379711");
        greenSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Slimy Green";
                dialog.close();
                Game.modifyLastPlayedCardColor(color);
                Game.dialogOpened = false;
            }
        });

        Div yellowSquare = new Div();
        yellowSquare.setWidth("300px");
        yellowSquare.setHeight("300px");
        yellowSquare.getStyle().set("background-color", "#ECD407");
        yellowSquare.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                color = "Safety Yellow";
                dialog.close();
                Game.modifyLastPlayedCardColor(color);
                Game.dialogOpened = false;
            }
        });

        row1.add(redSquare, blueSquare);
        row2.add(greenSquare, yellowSquare);

        dialog.add(row1, row2);
        dialog.open();
    }

    public String getColor(){
        return color;
    }

//    public String receivedColor() throws InterruptedException {
//        synchronized (this){
//            while(!colorChanged){
//                wait();
//            }
//        }
//        System.out.println(color);
//        return color;
//    }

    public void random() {
        Game.modifyLastPlayedCardColor(Card.randColor());
    }
}
