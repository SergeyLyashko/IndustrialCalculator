package viewmodel;

import controller.CalculatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.ViewController;

import javax.swing.*;
import java.util.*;

@Service("calculatorData")
public class CalculatorDataImpl implements CalculatorData {

    private static final String EMPTY = "";
    private Queue<String> data;
    private AppComponent width;
    private AppComponent length;
    private ViewController controller;

    @Autowired
    @Qualifier("width")
    public void setWidth(AppComponent width){
        this.width = width;
    }

    @Autowired
    @Qualifier("length")
    public void setLength(AppComponent length){
        this.length = length;
    }

    @Autowired
    public void setController(ViewController controller){
        this.controller = controller;
    }

    @Override
    public void add(Queue<String> selectedMenuItems){
        // TODO create List ??
        data = new LinkedList<>();
        data.addAll(selectedMenuItems);
    }

    private void addFieldsContain(){
        if(controller.isWidth() && !controller.isArea()){
            String containWidth = getFieldsContain(width);
            data.add(containWidth);
        }
        String containLength = getFieldsContain(length);
        data.add(containLength);
    }

    private String getFieldsContain(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getComponentParent();
        String textValue = parent.getText();
        if(textValue.equals(component.getName())) {
            textValue = EMPTY;
        }
        return textValue;
    }

    @Override
    public Queue<String> getData(){
        addFieldsContain();
        return data;
    }
}
