package viewcontroller;

import viewcomponents.common.AppComponent;
import viewmodel.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate(AppComponent component);

    void fieldActivate(AppComponent component);

    void registerFocusObserver(FocusActionObserver observer);

}
