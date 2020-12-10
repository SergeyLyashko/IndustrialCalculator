package view.model;

public interface State {

    void checkBoxSelect(boolean state);

    void actionState();

    void setFieldsOff();

    void setWidthOn();

    boolean isArea();

    boolean isWidth();

    void setLengthBehavior(FieldBehavior length);

    void setWidthBehavior(FieldBehavior width);
}
