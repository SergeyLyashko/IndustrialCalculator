package controller;

public interface ViewObserver {

    void messageUpdate(String message, boolean alert);

    void resultUpdate(double result);
}
