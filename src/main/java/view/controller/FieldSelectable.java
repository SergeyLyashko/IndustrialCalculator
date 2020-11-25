package view.controller;

public interface FieldSelectable extends AppComponent {

    void activate();

    void deactivate();

    String getName();

    void transformArea();
}
