package view.model;

import view.view.AppComponent;

public interface State {

    void checkBoxSelect(boolean state);

    void actionState();

    void setFieldsOff();

    void setWidthOn();

    boolean isArea();

    boolean isWidth();

    void setLength(AppComponent length);

    void setWidth(AppComponent width);
}
