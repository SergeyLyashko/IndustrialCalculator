package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class CalculatorAreaData implements ReceiveDataObserver {

    private final AppComponent area;
    private final List<String> data;

    public CalculatorAreaData(AppComponent area) {
        this.area = area;
        data = new LinkedList<>();
    }

    @Override
    public void keyActionUpdate(){
        update(area);
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
