package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CalculatorDataImpl implements CalculatorData {

    private final List<AppComponent> components;
    private final Queue<String> data;
    private boolean isArea;
    private boolean isWidth;

    public CalculatorDataImpl() {
        components = new ArrayList<>();
        data = new LinkedList<>();
    }

    @Override
    public void addData(AppComponent component){
        components.add(component);
    }

    @Override
    public void addData(String data){
        this.data.add(data);
    }

    @Override
    public void setWidthStatus(boolean status) {
        this.isWidth = status;
    }

    @Override
    public void keyActionUpdate(){
        if(isWidth && !isArea){
            components.forEach(this::update);
        }else{
            update(components.get(1));
        }
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        System.out.println("test double DATA: "+text);
        data.add(text);
    }

    @Override
    public Queue<String> getData(){
        return data;
    }

    @Override
    public void setAreaStatus(boolean status){
        this.isArea = status;
    }
}
