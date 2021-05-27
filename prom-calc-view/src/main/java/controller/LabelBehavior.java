package controller;

import components.common.AppComponent;

public interface LabelBehavior {

    void reset();

    void show(String value, boolean alert);

    void setComponent(AppComponent component);
}
