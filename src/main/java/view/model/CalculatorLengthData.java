package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

class CalculatorLengthData implements ReceiveDataObserver {

    private final AppComponent component;
    private final List<String> data;

    CalculatorLengthData(AppComponent component) {
        this.component = component;
        data = new LinkedList<>();
    }

    @Override
    public void keyActionUpdate(){
        update(component);
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        System.out.println("test DATA: "+text);
        data.add(text);
    }

    @Override
    public List<String> getData(){
        return data;
    }
}
