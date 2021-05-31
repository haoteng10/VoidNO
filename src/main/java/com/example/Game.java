package com.example;

import com.example.Views.GameView;
import com.example.components.BackCard;
import com.example.components.Card;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Card> playingCards;
//    private static BackCard drawCard;

    private static GamePlayer computer;
    private static GamePlayer player;

    public Game(boolean restart){
        if (restart) {
            //Playing card stack arraylist
            playingCards = new ArrayList<Card>();

            //Draw
            BackCard drawCard = new BackCard(-1);
            drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
                @Override
                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                    System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                    playerDrawCard(drawCard);
                }
            });

            GameView.addCardToCenterStack(drawCard);

            //Starting card
            Card firstCard = new Card(-1,false);
            playingCards.add(firstCard);
            GameView.addCardToCenterStack(firstCard);

            //Configuring computer player and actual player
            computer = new GamePlayer(true);
            player = new GamePlayer(false);
        }
    }

    private void playerDrawCard(BackCard drewCard) {
        //Unhidden the drew card for the player only
        Card card = new Card(player.getPlayingCards().size(), drewCard.getColor(), drewCard.getValue());
        //Give the player new card
        player.newCard(card);

        // Reset the card for next draw
        BackCard drawCard = new BackCard(-1);
        drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                playerDrawCard(drawCard);
            }
        });
        GameView.replaceDrawCard(drawCard);
    }

    private void computerDrawCard(BackCard drewCard){
        computer.newCard(drewCard);
    }

    public static boolean playCard(Card card){
        if (checkValidMove(card)) {
            playingCards.add(card);
            return true;
        }
        return false;
    }

    private static boolean checkValidMove(Card card) {
        if (playingCards.size() == 0) return true;

        Card prevCard = playingCards.get(playingCards.size()-1);
        return prevCard.getColor().equals(card.getColor()) || prevCard.getValue() == card.getValue();
    }

    public static GamePlayer getComputer() {
        return computer;
    }

    public static GamePlayer getPlayer() {
        return player;
    }

    public static Card getLastPlayedCard(){
        return playingCards.get(playingCards.size()-1);
    }
}
