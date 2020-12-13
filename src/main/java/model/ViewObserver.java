package model;

public interface ViewObserver {

    void messageUpdate(String message, boolean alert);

    void resultUpdate(String result, boolean alert);
}
