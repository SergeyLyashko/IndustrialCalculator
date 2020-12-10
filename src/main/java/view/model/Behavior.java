package view.model;

public interface Behavior {

    void fieldActivate();

    void fieldDeactivate();

    void areaActivate();

    void areaDeactivate();

    void registerObserver(KeyActionObserver viewModel);
}
