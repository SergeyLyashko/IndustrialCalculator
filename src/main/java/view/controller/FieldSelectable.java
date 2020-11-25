package view.controller;

import view.view.AppComponent;

public interface FieldSelectable extends AppComponent {

    void activate();

    void deactivate();

    String getName();

    void transformArea();
}
