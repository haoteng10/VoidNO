package com.example;

import com.example.Views.GameView;
import com.example.components.BackCard;
import com.example.components.Card;
import com.example.components.CardType;
import com.example.components.ColorSelector;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Card> playingCards;
    private static GamePlayer computer;
    private static GamePlayer player;

    private static GamePlayer currentMove;

    private static BackCard drawCard;

    public static boolean dialogOpened;

    public Game(boolean restart){
        if (restart) {
            //Playing card stack arraylist
            playingCards = new ArrayList<Card>();

            //Draw
            drawCard = new BackCard(-1, false);
            drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
                @Override
                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                    if (currentMove == player) {
                        System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                        playerDrawCard();
                    }
                }
            });

            GameView.addCardToCenterStack(drawCard);

            //Starting card
            Card firstCard = new Card(-1, true, false);
            playingCards.add(firstCard);
            GameView.addCardToCenterStack(firstCard);

            //Configuring computer player and actual player
            computer = new GamePlayer(true);
            player = new GamePlayer(false);

            //Set first move to the player
            currentMove = player;

            dialogOpened = false;
        }
    }

    public static void playerDrawCard() {
        //Unhidden the drew card
        Card card = new Card(player.getPlayingCards().size(), drawCard.getType(), drawCard.getColor(), drawCard.getValue());
        //Give the player new card
        player.newCard(card);

        // Reset the card for next draw
        drawCard = new BackCard(-1, false);
        drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                playerDrawCard();
            }
        });
        GameView.replaceDrawCard(drawCard);

        //Change position
        Game.switchPosition();

        if (Game.isComputerTurn()){
            Game.getComputer().autoplay();
        }
    }

    public static void computerDrawCard(){
        System.out.println("Computer drawing card!");

        BackCard card = new BackCard(computer.getPlayingCards().size(), drawCard.getType(), drawCard.getColor(), drawCard.getValue());

        computer.newCard(card);

        // Reset the card for next draw
        drawCard = new BackCard(-1, true);
        drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                playerDrawCard();
            }
        });
        GameView.replaceDrawCard(drawCard);

        //Change position
        Game.switchPosition();
    }

    public static boolean playCard(GamePlayer player, Card card) {
        if (checkValidMove(player, card)) {
            playingCards.add(card);

            if (card.getType() == CardType.WILD_COLOR || card.getType() == CardType.WILD_PLUS_FOUR){
//                Future<String> futureSelectedColor = new ColorSelector().select();
                ColorSelector selector = new ColorSelector();

                System.out.println("Current move: " + currentMove);
                System.out.println("Player: " + player + " Computer? " + player.isComputer());

                if (currentMove == Game.player) {
                    System.out.println("Choose a color!");
                    selector.selectSync();
                    dialogOpened = true;
                } else {
                    selector.random();
                }
            }

            return true;
        }
        return false;
    }

    public static boolean checkValidMove(GamePlayer player, Card card) {
        if (playingCards.size() == 0) return true;

        if (currentMove != player) return false;

        Card prevCard = playingCards.get(playingCards.size()-1);

        //Go Wild cards
        if (card.getType() == CardType.WILD_COLOR || card.getType() == CardType.WILD_PLUS_FOUR){
            return true;
        }
        //Same action cards
        if (card.getType() != CardType.NUMBER && card.getType() == prevCard.getType()){
            return true;
        }

        return prevCard.getColor().equals(card.getColor()) || (card.getValue() != -1 && prevCard.getValue() == card.getValue());
    }

    public static void switchPosition(){

        //Change position
        if (currentMove == player){
            System.out.println("To: Computer");
            currentMove = computer;
        } else {
            System.out.println("To: Player");
            currentMove = player;
        }
    }

    public static boolean isComputerTurn(){
        return currentMove == computer;
    }

    public static GamePlayer getComputer() {
        return computer;
    }

    public static GamePlayer getPlayer() {
        return player;
    }

    public static GamePlayer getCurrentMove() {
        return currentMove;
    }

    public static Card getLastPlayedCard(){
        Card last = playingCards.get(playingCards.size()-1);
        System.out.println("Color: " + last.getColor());
        return new Card(-1, last.getType(), last.getColor(), last.getValue());
    }

    public static void modifyLastPlayedCardColor(String color){
        System.out.println("Changing wild card to : " + color);
        playingCards.get(playingCards.size()-1).setColor(color);
        GameView.replacePlayingCard(playingCards.get(playingCards.size()-1));
    }
}
