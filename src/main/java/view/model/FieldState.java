package view.model;

import view.controller.FieldSelectable;

public interface FieldState {

    void menuSelected();

    void checkBoxState(boolean checkBoxState);

    void fieldAction(FieldSelectable width, FieldSelectable length);

}
