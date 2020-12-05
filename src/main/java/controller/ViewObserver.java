package controller;

public interface ViewObserver {

    void messageUpdate(String message);

    void resultUpdate(double result);
}
