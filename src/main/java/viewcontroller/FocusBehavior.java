package viewcontroller;

public interface FocusBehavior {

    void deactivate();

    void activate();

    void registerFocusObserver(FocusActionObserver observer);
}
