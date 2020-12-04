package model;

import controller.MessageObserver;

public interface MessageSubject {

    void notifyObservers();

    void registerObserver(MessageObserver observer);

}
