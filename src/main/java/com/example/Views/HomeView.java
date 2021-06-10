package com.example.Views;

import com.example.components.AudioPlayer;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@CssImport("./styles/home.css")
@Route("")
public class HomeView extends VerticalLayout implements PageConfigurator {
    public HomeView(){
        setClassName("home-view");
        setHeight("100vh");
        setWidth("100vw");

        H1 h1 = new H1("VoidNO");
        H2 h2 = new H2("Made by VoidGames for educational purposes only");

        Button button = new Button();
        button.setText("Play!");
        button.setHeight("100px");
        button.setWidth("300px");
        button.addClickListener(e -> UI.getCurrent().navigate(GameView.class));

        Button button2 = new Button();
        button2.setText("Rules");
        button2.setHeight("100px");
        button2.setWidth("300px");
        button2.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                Dialog dialog = new Dialog();

                VerticalLayout vl = new VerticalLayout();

                /*
                Objective: The first player to play all of the cards wins the round.
                There are 7 starting cards for each player.

                Types of cards:
                1. Colored number cards - play when you have the same number or color
                2. Colored actions cards (+2 and skip)  - play when you have the same color
                   Note: Reverse is removed from this game due to 1v1
                3. Go Wild cards (Wild and Wild Draw 4) - play anytime & allows you to select a new color

                 */

                H1 h1 = new H1("Rules of VoidNo");
                h1.getStyle().set("font-size", "3em");
                h1.getStyle().set("font-family", "Serif");

                Div objDiv = new Div();
                Span obj = new Span("Objective: ");
                Text objText = new Text("The first player to play all of the cards wins the round.");
                objDiv.add(obj, objText);

                Div numStartingCards = new Div(new Text("There are 7 starting cards for each player."));

                Div typesOfCards = new Div(new Text("Types of cards:"));

                ListItem coloredNumberCards = new ListItem("Colored number cards - play when you have the same number or color");
                ListItem coloredActionCards = new ListItem("Colored actions cards (+2 and skip)  - play when you have the same color");
                ListItem goWildCards = new ListItem("Go Wild cards (Wild and Wild Draw 4) - play anytime & allows you to select a new color");

                OrderedList ol = new OrderedList(coloredNumberCards, coloredActionCards, goWildCards);

                Div notes = new Div();
                Text notesA = new Text("Note: ");
                Text noReverse = new Text("no reverse cards due to 1v1 configuration");
                notes.add(notesA, noReverse);
                notes.getStyle().set("color", "#0aff54");
                notes.getStyle().set("font-style", "italic");

                vl.add(h1, objDiv, numStartingCards, typesOfCards, ol, notes);

                dialog.add(vl);
                dialog.open();
            }
        });

        Div buttonDiv = new Div();
        buttonDiv.add(button, button2);
        buttonDiv.addClassName("button-div");

        AudioPlayer player = new AudioPlayer();
        player.setSource("music/Better Days.mp3");

        Div audioDiv = new Div();
        audioDiv.add(player);
        audioDiv.addClassName("audio-div");

        add(h1, h2, buttonDiv, audioDiv);
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        initialPageSettings.addLink("stylesheet","https://fonts.googleapis.com/css2?family=Luckiest+Guy&display=swap");
    }
}
