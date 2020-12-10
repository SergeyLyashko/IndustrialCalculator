package view.model;

public interface State {

    void checkBoxSelect(boolean state);

    void action();

    void fieldsOff();

    void widthOn();

    boolean isArea();

    boolean isWidth();

    void setLengthBehavior(FieldBehavior length);

    void setWidthBehavior(FieldBehavior width);
}
