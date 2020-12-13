package viewmodel;

import viewcontroller.KeyActionObserver;

public interface KeyActionSubject {

    void notifyObservers();

    void registerObserver(KeyActionObserver keyActionObserver);

}
