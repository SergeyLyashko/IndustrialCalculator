package controller;

public interface ViewSubject {

    void notifyResultObservers(String mass, boolean alert);

    void notifyMessageObservers(String message, boolean alert);

    void registerObserver(ViewObserver observer);

}
