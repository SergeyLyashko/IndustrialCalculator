package view.fieldsbehavior;

import view.viewmodel.KeyActionObserver;

public interface Behavior {

    void registerObserver(KeyActionObserver observer);

    void fieldDeactivate();

    void fieldActivate();

    void areaActivate();

    void areaDeactivate();
}
