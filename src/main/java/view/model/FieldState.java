package view.model;

import view.view.AppComponent;

public interface FieldState {

    void menuSelected();

    void checkBoxState(boolean checkBoxState);

    void fieldAction(AppComponent width, AppComponent length);

}
