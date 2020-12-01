package view.model.behavior;

import view.model.CalculatorDataObserver;

public interface KeyAction {

    void notifyObservers();

    void registerObserver(CalculatorDataObserver keyActionObserver);
}
