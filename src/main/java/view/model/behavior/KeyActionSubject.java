package view.model.behavior;

import view.model.KeyActionObserver;

public interface KeyActionSubject {

    void notifyObservers();

    void registerObserver(KeyActionObserver keyActionObserver);
}
