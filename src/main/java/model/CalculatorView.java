package model;

public interface CalculatorView {

    void messageUpdate(String message, boolean alert);

    void resultUpdate(String result, boolean alert);
}
