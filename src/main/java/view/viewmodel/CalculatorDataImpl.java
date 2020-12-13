package view.viewmodel;

import controller.CalculatorData;
import view.AppComponent;

import javax.swing.*;
import java.util.*;

class CalculatorDataImpl implements CalculatorData {

    private static final String EMPTY = "";
    private final List<AppComponent> components;
    private final Queue<String> queueItems;
    private Queue<String> data;

    CalculatorDataImpl(Queue<String> queueItems, AppComponent width, AppComponent length, Behavior fieldBehavior) {
        this.queueItems = queueItems;
        components = new ArrayList<>();
        components.add(width);
        components.add(length);
        createData();
        componentsUpdate(fieldBehavior);
    }

    private void createData(){
        data = new LinkedList<>();
        data.addAll(queueItems);
    }

    private void componentsUpdate(Behavior fieldBehavior){
        if(fieldBehavior.isWidth() && !fieldBehavior.isArea()){
            components.forEach(this::update);
        }else {
            update(components.get(1));
        }
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String textValue = parent.getText();
        if(textValue.equals(component.getName())) {
            textValue = EMPTY;
        }
        data.add(textValue);
    }

    @Override
    public Queue<String> getData(){
        return data;
    }
}
