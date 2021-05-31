package com.example;

import com.example.components.Card;

import java.util.ArrayList;

public class Game {
    private boolean isStarted;
    private ArrayList<Card> playingCards;

    public Game(){
        isStarted = true;
    }

    public boolean playCard(Card card){
        if (checkValidMove(card)) {
            playingCards.add(card);
            return true;
        }
        return false;
    }

    private boolean checkValidMove(Card card) {
        if (playingCards.size() == 0) return true;

        Card prevCard = playingCards.get(playingCards.size()-1);
        if (prevCard.getColor().equals(card.getColor()) || prevCard.getValue() == card.getValue()){
            return true;
        }
        return false;
    }
}
