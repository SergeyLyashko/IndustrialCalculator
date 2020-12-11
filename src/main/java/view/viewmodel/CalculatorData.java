package view.viewmodel;

import view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CalculatorData {

    private static final String EMPTY = "";
    private final List<AppComponent> components;
    private final Queue<String> data;

    CalculatorData(String assortment, String type, String number, AppComponent width, AppComponent length, FieldState fieldState) {
        data = new LinkedList<>();
        data.add(assortment);
        data.add(type);
        data.add(number);
        components = new ArrayList<>();
        components.add(width);
        components.add(length);
        componentsUpdate(fieldState);
    }

    private void componentsUpdate(FieldState fieldState){
        if(fieldState.isWidth() && !fieldState.isArea()){
            components.forEach(this::update);
        }else {
            update(components.get(1));
        }
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        if(text.equals(component.getName())) {
            text = EMPTY;
        }
        data.add(text);
    }

    Queue<String> getData(){
        return data;
    }
}
