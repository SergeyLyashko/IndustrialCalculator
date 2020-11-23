package view.calculator.state;


import view.calculator.fields.FieldSelectable;

public interface FieldState {

    void menuSelected(FieldSelectable width);

    void turnNumbers();

    void fieldOn(FieldSelectable width);

}
