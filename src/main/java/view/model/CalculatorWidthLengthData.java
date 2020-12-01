package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class CalculatorWidthLengthData implements ReceiveDataObserver {

    private final List<String> data;
    private final AppComponent width;
    private final AppComponent length;

    public CalculatorWidthLengthData(AppComponent width, AppComponent length) {
        data = new LinkedList<>();
        this.width = width;
        this.length = length;
    }

    @Override
    public void keyActionUpdate(){
        update(width);
        update(length);
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
}
