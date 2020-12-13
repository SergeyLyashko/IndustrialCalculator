package viewcontroller;

import view.viewmodel.KeyActionObserver;

public interface FieldBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void areaActivate();

    void areaDeactivate();

    void registerObserver(KeyActionObserver observer);

}
