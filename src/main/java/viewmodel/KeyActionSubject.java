package viewmodel;

import viewcontroller.KeyActionObserver;

// TODO DEL ???
public interface KeyActionSubject {

    void notifyObservers();

    void registerObserver(KeyActionObserver keyActionObserver);
}
