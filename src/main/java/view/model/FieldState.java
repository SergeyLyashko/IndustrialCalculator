package view.model;

import view.view.AppComponent;

public interface FieldState {

    default void menuSelected(){}

    default void checkBoxState(boolean checkBoxState){}

    default void fieldAction(AppComponent width, AppComponent length){}

}
