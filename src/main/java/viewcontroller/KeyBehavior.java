package viewcontroller;

public interface KeyBehavior {

    void fieldDeactivate();

    void registerKeyObserver(KeyActionObserver observer);

    void fieldActivate();
}
