package controller;

import viewcontroller.Controller;

import java.util.Queue;

public class ControllerImpl implements Controller {

    private final CalculatorModel model;

    public ControllerImpl(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void setData(CalculatorData calculatorData) {
        Queue<String> data = calculatorData.getData();
        model.setData(data);
        model.calculation();
    }
}
