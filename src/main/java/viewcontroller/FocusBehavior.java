package viewcontroller;

import viewcomponents.common.AppComponent;
import viewmodel.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void registerFocusObserver(FocusActionObserver observer);

    void setComponent(AppComponent component);
}
