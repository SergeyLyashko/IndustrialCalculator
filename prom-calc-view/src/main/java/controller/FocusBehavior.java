package controller;

import components.common.AppComponent;
import model.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate(AppComponent component);

    void fieldActivate(AppComponent component);

    void registerFocusObserver(FocusActionObserver observer);

}
