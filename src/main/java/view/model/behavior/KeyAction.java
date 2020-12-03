package view.model.behavior;

import view.model.CalculatorData;

public interface KeyAction {

    void notifyObservers();

    void registerObserver(CalculatorData keyActionObserver);
}
