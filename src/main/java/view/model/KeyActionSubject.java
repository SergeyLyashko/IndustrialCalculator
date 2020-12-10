package view.model;

public interface KeyActionSubject {

    void notifyObservers();

    void registerObserver(KeyActionObserver keyActionObserver);

}
