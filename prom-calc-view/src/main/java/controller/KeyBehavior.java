package controller;

import ui.AppComponent;
import model.KeyActionObserver;

public interface KeyBehavior {

    void fieldDeactivate(AppComponent component);

    void registerKeyObserver(KeyActionObserver observer);

    void fieldActivate(AppComponent component);

}
