package controller;

import model.CalculatorModel;

import java.util.Queue;

public class ControllerImpl implements Controller {

    private final CalculatorModel model;

    public ControllerImpl(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void setDetailData(Queue<String> detailData) {
        model.setDetailData(detailData);
    }
}
