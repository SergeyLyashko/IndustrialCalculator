package model;

public interface View {

    void messageUpdate(String message, boolean alert);

    void resultUpdate(String result, boolean alert);
}
