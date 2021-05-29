package controller;

import ui.UiComponent;

public interface LabelBehavior {

    void reset();

    void show(String value, boolean alert);

    void setComponent(UiComponent component);
}
