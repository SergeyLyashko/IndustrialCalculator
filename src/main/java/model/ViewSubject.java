package model;

import controller.ViewObserver;

public interface ViewSubject {

    void notifyResultObservers(double result);

    void notifyMessageObservers(String message, boolean alert);

    void registerObserver(ViewObserver observer);

}
