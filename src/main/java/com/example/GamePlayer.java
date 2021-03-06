package com.example;

import com.example.Views.GameView;
import com.example.Views.HomeView;
import com.example.components.BackCard;
import com.example.components.Card;
import com.example.components.CardType;
import com.example.components.SmallPopUp;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.ArrayList;

public class GamePlayer {
    private final ArrayList<Card> playingCards;
    private final boolean isComputer;

    public GamePlayer(boolean isComputer){
        this.isComputer = isComputer;

        playingCards = new ArrayList<Card>();
        if (isComputer){
            for (int i = 0; i < 7; i++){
                BackCard card = new BackCard(playingCards.size(), false);
                playingCards.add(card);
                // Call MainView and add to the computer stack
                GameView.addCardToComputerStack(card);
            }
        } else {
            for (int i = 0; i < 7; i++){
                Card card = new Card(playingCards.size(), false);
                card.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                        System.out.println(card.getColor() + " " + card.getValue());
                        playCard(card.getIndex());
                    }
                });
                playingCards.add(card);
                //Call MainView and add to the player stack
                GameView.addCardToPlayerStack(card);
            }
        }
    }

    public void playCard(int cardIndex) {
        if (Game.playCard(this, playingCards.get(cardIndex))) {

            System.out.println("Layer 2: ");

            //Remove card from the player's stack
            Card card = playingCards.remove(cardIndex);

            //Remove the card from the GUI
            if (isComputer) {
                GameView.removeCardFromComputerStack((BackCard) card);
            } else {
                GameView.removeCardFromPlayerStack(card);
            }
            GameView.replacePlayingCard(new Card(-1, card.getType(), card.getColor(), card.getValue()));

            //Update all cards in the stack to make sure they have the correct index
            updateIndex();

            //Check if the player wins
            checkIfWinning();

            //Note: The opponent can move before the user selects a color

            System.out.println("C: " + Game.isComputerTurn());

            //Change position
            Game.switchPosition();

            if (Game.isComputerTurn()){
                Game.getComputer().autoplay();
            }

        }
    }

    private void checkIfWinning() {
        if (playingCards.size() == 0){
            Dialog dialog = new Dialog();
            dialog.setWidth("600px");
            dialog.setHeight("200px");
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);

            HorizontalLayout layout1 = new HorizontalLayout();

            Div text = new Div();
            if (isComputer()) {
                text.add("The computer won this game. Try again next time!");
            } else {
                text.add("You won the game! Woohoo!");
            }
            text.addClassName("winning-text");

            layout1.add(text);
            layout1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            HorizontalLayout layout2 = new HorizontalLayout();

            Button exit = new Button("Menu", new Icon(VaadinIcon.ARROW_LEFT));
            exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            exit.addClickListener(e -> {
                UI.getCurrent().navigate(HomeView.class);
                dialog.close();
            });
            exit.addClassName("exit-button");

            layout2.add(exit);
            layout2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            dialog.add(layout1, layout2);

            dialog.open();
        }
    }

    private void updateIndex(){
        for (int i = 0; i < playingCards.size(); i++){
            playingCards.get(i).setIndex(i);
        }
    }

    public ArrayList<Card> getPlayingCards() {
        return playingCards;
    }

    public void newCard(Card card) {
        if (!isComputer) {
            card.addClickListener(new ComponentEventListener<ClickEvent<Div>>() {
                @Override
                public void onComponentEvent(ClickEvent<Div> divClickEvent) {
                    System.out.println(card.getColor() + " " + card.getValue());
                    playCard(card.getIndex());
                }
            });
        }

        playingCards.add(card);

        // Configure the GUI for new cards
        if (isComputer) {
            GameView.addCardToComputerStack((BackCard) card);
        } else {
            GameView.addCardToPlayerStack(card);
        }

        //Reset the cards' index values
        updateIndex();
    }

    public void autoplay(){
        System.out.println("=== Autoplay by computer ===");

        for (int i = playingCards.size() - 1; i >= 0; i--) {
            if (Game.checkValidMove(this, playingCards.get(i))) {
                String msg = "Computer: ";

                if (playingCards.get(i).getType() == CardType.WILD_COLOR) {
                    msg += "playing a wild card!";
                } else if (playingCards.get(i).getType() == CardType.WILD_PLUS_FOUR){
                    msg += "go wild +4!";
                } else if (playingCards.get(i).getType() == CardType.ACTION_SKIP){
                    msg += "skipped your round!";
                } else if (playingCards.get(i).getType() == CardType.ACTION_PLUS_TWO){
                    msg += "giving you 2 extra cards!";
                } else {
                    msg +=  playingCards.get(i).getType() + " " + playingCards.get(i).getColor() + " " + playingCards.get(i).getValue();
                }

                new SmallPopUp(10000, msg);
                playCard(playingCards.get(i).getIndex());
                return;
            }
        }
        Game.computerDrawCard();
        Game.switchPosition();
    }

    public boolean isComputer(){
        return isComputer;
    }
}
