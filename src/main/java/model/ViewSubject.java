package model;

import controller.ViewObserver;

public interface ViewSubject {

    // TODO for result ?
    void notifyObservers();

    //void notifyMessageObservers(String message);

    void registerObserver(ViewObserver observer);

}
