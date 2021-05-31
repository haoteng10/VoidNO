package com.example;

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

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    public MainView() {

        //Displaying computer cards
        HorizontalLayout topStack = new HorizontalLayout();
        topStack.setWidth("100%");
        topStack.setJustifyContentMode(JustifyContentMode.CENTER);
        ArrayList<BackCard> enemyCamoCards = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            BackCard card = new BackCard();
            enemyCamoCards.add(card);
            topStack.add(card);
        }

        // Arena
        HorizontalLayout centerStack = new HorizontalLayout();
        centerStack.setWidth("100%");
        centerStack.setJustifyContentMode(JustifyContentMode.BETWEEN);
        Card lastCard = new Card(false);
        centerStack.add(lastCard);

        // Displaying player cards
        HorizontalLayout bottomStack = new HorizontalLayout();
        bottomStack.setWidth("100%");
        bottomStack.setJustifyContentMode(JustifyContentMode.CENTER);
        ArrayList<Card> playerCards = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            Card card = new Card();
            card.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
                @Override
                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                    System.out.println(card.getColor() + " " + card.getValue());
                }
            });


            //Add to player cards stack & bottom stack for rendering
            playerCards.add(card);
            bottomStack.add(card);
        }

        add(topStack, centerStack, bottomStack);
    }

}
