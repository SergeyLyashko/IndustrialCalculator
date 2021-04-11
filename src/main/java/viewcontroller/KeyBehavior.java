package viewcontroller;

import viewcomponents.common.AppComponent;
import viewmodel.KeyActionObserver;

public interface KeyBehavior {

    void fieldDeactivate();

    void registerKeyObserver(KeyActionObserver observer);

    void fieldActivate();

    void setComponent(AppComponent component);
}
