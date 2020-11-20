package view.calculator.fields;

import view.AppComponent;

public interface FieldSelectable extends AppComponent {

    void activate();

    void deactivate();

    String getName();

    void complexAreaDeactivate();

    void complexAreaActivate();
}
