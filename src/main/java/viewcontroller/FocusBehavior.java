package viewcontroller;

import view.AppComponent;
import viewmodel.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void registerFocusObserver(FocusActionObserver observer);

    void setComponent(AppComponent component);
}
