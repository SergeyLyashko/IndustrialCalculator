package view.viewmodel;

import controller.CalculatorData;
import view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class CalculatorDataImpl implements CalculatorData {

    private static final String EMPTY = "";
    private final List<AppComponent> components;
    private final Queue<String> queueItems;

    CalculatorDataImpl(Queue<String> queueItems, AppComponent width, AppComponent length, FieldState fieldState) {
        this.queueItems = queueItems;
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
        String textValue = parent.getText();
        if(textValue.equals(component.getName())) {
            textValue = EMPTY;
        }
        queueItems.add(textValue);
    }

    @Override
    public Queue<String> getQueueItems(){
        return queueItems;
    }
}
