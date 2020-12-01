package view.model;

public interface KeyAction {

    void notifyObservers();

    void registerObserver(ReceiveDataObserver keyActionObserver);
}
