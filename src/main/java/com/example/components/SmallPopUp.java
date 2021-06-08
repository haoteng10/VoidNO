package com.example.components;

import com.vaadin.flow.component.notification.Notification;

public class SmallPopUp {

    public SmallPopUp(String message){
        Notification notification = new Notification(message, 3000, Notification.Position.BOTTOM_END);
        notification.open();
    }
}
