package view.model.state;

import view.view.AppComponent;

public interface FieldState {

    default void checkBoxState(boolean checkBoxState){}

    default void action(AppComponent component){}

}
