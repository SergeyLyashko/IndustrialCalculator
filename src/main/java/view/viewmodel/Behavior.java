package view.viewmodel;

public interface Behavior {

    void registerObserver(KeyActionObserver observer);

    void fieldDeactivate();

    void fieldActivate();

    void areaActivate();

    void areaDeactivate();
}
