package view.calculator.state;


import view.calculator.fields.FieldSelectable;

public interface FieldState {

    void menuSelected(FieldSelectable width, FieldSelectable length);

    void checkBoxState(boolean checkBoxState);

    void turnNumbers();

    void fieldAction(FieldSelectable width, FieldSelectable length);

}
