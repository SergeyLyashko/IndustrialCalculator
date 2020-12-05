package controller;

import model.CalculatorModel;

import java.util.Queue;

public class ControllerImpl implements Controller {

    private final CalculatorModel model;

    public ControllerImpl(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void setData(Queue<String> detailData) {
        model.setData(detailData);
        model.calculation();
    }
}
