package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CalculatorData implements CalculatorDataObserver, DataReceiver {

    private final AppComponent[] components;
    private final List<String> data;
    private boolean areaStatus;

    public CalculatorData(AppComponent[] components) {
        this.components = components;
        data = new LinkedList<>();
    }

    @Override
    public void keyActionUpdate(){
        Arrays.stream(components).forEach(this::update);
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        System.out.println("test double DATA: "+text);
        data.add(text);
    }

    @Override
    public List<String> getData(){
        return data;
    }

    @Override
    public boolean isArea() {
        return areaStatus;
    }

    @Override
    public void setAreaStatus(boolean status){
        this.areaStatus = status;
    }
}
