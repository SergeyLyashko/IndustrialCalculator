package viewcontroller;

import viewmodel.FocusActionObserver;

public interface FocusBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void registerFocusObserver(FocusActionObserver observer);
}
