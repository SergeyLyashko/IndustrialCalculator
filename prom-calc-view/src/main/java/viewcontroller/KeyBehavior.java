package viewcontroller;

import viewcomponents.common.AppComponent;
import viewmodel.KeyActionObserver;

public interface KeyBehavior {

    void fieldDeactivate(AppComponent component);

    void registerKeyObserver(KeyActionObserver observer);

    void fieldActivate(AppComponent component);

}
