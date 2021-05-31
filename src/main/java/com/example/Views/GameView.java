package com.example.Views;

import com.example.Game;
import com.example.components.BackCard;
import com.example.components.Card;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@Route("/game")
@CssImport("./styles/shared-styles.css")
public class GameView extends VerticalLayout {

    private static HorizontalLayout topStack;
    private static HorizontalLayout centerStack;
    private static HorizontalLayout bottomStack;

    public GameView() {

        topStack = new HorizontalLayout();
        centerStack = new HorizontalLayout();
        bottomStack = new HorizontalLayout();

        //Displaying computer cards
        topStack.setWidth("100%");
        topStack.setJustifyContentMode(JustifyContentMode.CENTER);

        // Arena
        centerStack.setWidth("100%");
        centerStack.setJustifyContentMode(JustifyContentMode.CENTER);

        // Displaying player cards
        bottomStack.setWidth("100%");
        bottomStack.setJustifyContentMode(JustifyContentMode.CENTER);

        // Start game
        new Game(true);

        add(topStack, centerStack, bottomStack);
    }

    public static void addCardToComputerStack(BackCard card) {
        topStack.add(card);
    }

    public static void addCardToPlayerStack(Card card){
        bottomStack.add(card);
    }

    public static void removeCardFromComputerStack(BackCard card){
        topStack.remove(card);
    }

    public static void removeCardFromPlayerStack(Card card){
        bottomStack.remove(card);
    }

    public static void addCardToCenterStack(Card card) {
        centerStack.add(card);
    }

    public static void replaceDrawCard(BackCard card){
        try {
            centerStack.removeAll();
        } catch (Exception ignored){
        }
        centerStack.add(card);
        centerStack.add(Game.getLastPlayedCard());
    }

    public static void replacePlayingCard(Card card){
        centerStack.remove(centerStack.getComponentAt(centerStack.getComponentCount()-1));
        centerStack.add(card);
    }
}