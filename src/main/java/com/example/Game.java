package com.example;

import com.example.Views.GameView;
import com.example.components.*;
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
        super();

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

                        //Change position
                        Game.switchPosition();

                        if (Game.isComputerTurn()){
                            Game.getComputer().autoplay();
                        }
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
        System.out.println("Player drew a card!");
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

                //Change position
                Game.switchPosition();

                if (Game.isComputerTurn()){
                    Game.getComputer().autoplay();
                }
            }
        });
        GameView.replaceDrawCard(drawCard);

    }

    public static void computerDrawCard(){
        System.out.println("Computer drew a card!");

        BackCard card = new BackCard(computer.getPlayingCards().size(), drawCard.getType(), drawCard.getColor(), drawCard.getValue());

        computer.newCard(card);

        // Reset the card for next draw
        drawCard = new BackCard(-1, false);
        drawCard.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
            @Override
            public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                System.out.println(drawCard.getColor() + " " + drawCard.getValue());
                playerDrawCard();

                //Change position
                Game.switchPosition();

                if (Game.isComputerTurn()){
                    Game.getComputer().autoplay();
                }
            }
        });
        GameView.replaceDrawCard(drawCard);
    }

    public static boolean playCard(GamePlayer player, Card card) {
        if (checkValidMove(player, card)) {
            playingCards.add(card);

            System.out.println("Card Type: " + card.getType());

            if (card.getType() == CardType.WILD_COLOR || card.getType() == CardType.WILD_PLUS_FOUR){

                //Moved by a computer
                if (currentMove == Game.computer){
                    new ColorSelector().random();
                    if (card.getType() == CardType.WILD_PLUS_FOUR){
                        for (int i = 0; i < 4; i++){
                            playerDrawCard();
                        }
                        Game.switchPosition();
                        return true;
                    }
                }

                //Moved by a player
                if (currentMove == Game.player){
                    if (!card.isSelectedColor()){
                        new SmallPopUp("Make sure to select a color before placing!");
                        return false;
                    }

                    if (card.getType() == CardType.WILD_PLUS_FOUR){
                        for (int i = 0; i < 4; i++){
                            computerDrawCard();
                        }
                        Game.switchPosition();
                        return true;
                    }
                }

            }

            //Action: Skip cards
            if (card.getType() == CardType.ACTION_SKIP){
                Game.switchPosition();
            }

            //Action: +2 cards
            if (card.getType() == CardType.ACTION_PLUS_TWO){
                if (currentMove == Game.player){
                    for (int i = 0; i < 2; i++){
                        computerDrawCard();
                    }
                    Game.switchPosition();
                } else {
                    for (int i = 0; i < 2; i++){
                        playerDrawCard();
                    }
                    Game.switchPosition();
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkValidMove(GamePlayer player, Card card) {
        if (playingCards.size() == 0) return true;

        System.out.println("Check valid turn, Computer: " + Game.isComputerTurn());

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
