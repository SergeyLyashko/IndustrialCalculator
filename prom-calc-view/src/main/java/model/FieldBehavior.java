package model;

import ui.UiComponent;

public interface FieldBehavior {

    void fieldDeactivate(UiComponent component);

    void fieldActivate(UiComponent component);

    void areaActivate(UiComponent component);

    void areaDeactivate(UiComponent component);

}
