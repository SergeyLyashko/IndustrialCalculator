package model;

import controller.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ui.UiComponent;

import javax.swing.*;
import java.util.*;

@Service("calculatorData")
class CalculatorDataImpl implements CalculatorData {

    private static final String EMPTY = "";
    @Autowired
    @Qualifier("width")
    private UiComponent width;
    @Autowired
    @Qualifier("length")
    private UiComponent length;
    @Autowired
    private ViewController controller;
    private Map<String, String> selectedMenuItems;


    @Override
    public void add(Map<String, String> selectedMenuItems){
        this.selectedMenuItems = selectedMenuItems;
    }

    private List<String> receiveFieldsValue(){
        LinkedList<String> data = new LinkedList<>();
        if(controller.isWidth() && !controller.isArea()){
            String containWidth = getFieldsContain(width);
            data.add(containWidth);
        }
        String containLength = getFieldsContain(length);
        data.add(containLength);
        return data;
    }

    private String getFieldsContain(UiComponent component){
        JTextField parent = (JFormattedTextField) component.getComponentParent();
        String textValue = parent.getText();
        if(textValue.equals(component.getName())) {
            textValue = EMPTY;
        }
        return textValue;
    }

    @Override
    public List<String> getFieldsData(){
        return receiveFieldsValue();
    }

    @Override
    public Map<String, String> getSelectedMenuItems(){
        return selectedMenuItems;
    }
}
