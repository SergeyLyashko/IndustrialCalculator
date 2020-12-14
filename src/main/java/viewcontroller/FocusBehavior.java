package viewcontroller;

public interface FocusBehavior {

    void fieldDeactivate();

    void fieldActivate();

    void registerFocusObserver(FocusActionObserver observer);
}
