package controller;

import ui.UiComponent;

public interface FieldAction {

    void setComponent(UiComponent component);

    void deactivate();

    void activate();

    void areaActivate();

    void areaDeactivate();
}
