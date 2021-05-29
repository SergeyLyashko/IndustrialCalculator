package controller;

import ui.AppComponent;

public interface FieldBehavior {

    void fieldDeactivate(AppComponent component);

    void fieldActivate(AppComponent component);

    void areaActivate(AppComponent component);

    void areaDeactivate(AppComponent component);

}
