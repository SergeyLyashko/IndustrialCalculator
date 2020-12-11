package view.viewmodel;

public interface KeyActionSubject {

    void notifyObservers();

    void registerObserver(KeyActionObserver keyActionObserver);

}
