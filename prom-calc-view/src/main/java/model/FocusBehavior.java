package model;

import ui.UiComponent;

public interface FocusBehavior {

    void fieldDeactivate(UiComponent component);

    void fieldActivate(UiComponent component);
}
