package view.controller;

public interface FieldState {

    void menuSelected(FieldSelectable width, FieldSelectable length);

    void checkBoxState(boolean checkBoxState);

    void turnNumbers();

    void fieldAction(FieldSelectable width, FieldSelectable length);

}
