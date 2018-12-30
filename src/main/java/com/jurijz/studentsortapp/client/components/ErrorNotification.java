package com.jurijz.studentsortapp.client.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Created by jurijz on 12/25/2018.
 */
public class ErrorNotification extends Notification {

    public ErrorNotification(String errorMsg) {
        super();
        add(notificationComponents(errorMsg));
        setPosition(Position.MIDDLE);
        open();
    }

    private Component notificationComponents(String errorMsg) {
        VerticalLayout layout = new VerticalLayout();
        Label label = new Label(errorMsg);
        label.getStyle().set("color", "red");

        Button button = new Button("OK", e -> close());
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(label, button);

        return layout;
    }
}
