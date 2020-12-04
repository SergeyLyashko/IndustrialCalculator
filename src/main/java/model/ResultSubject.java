package model;

import controller.ResultObserver;

public interface ResultSubject {

    void notifyObservers();

    void registerObserver(ResultObserver observer);
}
