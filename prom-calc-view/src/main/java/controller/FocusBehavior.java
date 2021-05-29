package controller;

import ui.AppComponent;
import model.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate(AppComponent component);

    void fieldActivate(AppComponent component);

    void registerFocusObserver(FocusActionObserver observer);

}
