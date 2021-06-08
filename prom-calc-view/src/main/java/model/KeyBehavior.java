package model;

import ui.UiComponent;
import model.KeyActionObserver;

public interface KeyBehavior {

    void fieldDeactivate(UiComponent component);

    void registerKeyObserver(KeyActionObserver observer);

    void fieldActivate(UiComponent component);

}
