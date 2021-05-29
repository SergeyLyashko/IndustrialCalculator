package controller;

import ui.UiComponent;
import model.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate(UiComponent component);

    void fieldActivate(UiComponent component);

    void registerFocusObserver(FocusActionObserver observer);

}
