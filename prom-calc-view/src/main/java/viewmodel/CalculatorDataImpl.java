package viewmodel;

import controller.CalculatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcomponents.common.ViewController;

import javax.swing.*;
import java.util.*;

@Service("calculatorData")
class CalculatorDataImpl implements CalculatorData {

    private static final String EMPTY = "";
    private AppComponent width;
    private AppComponent length;
    private ViewController controller;
    private Map<String, String> selectedMenuItems;

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

    private String getFieldsContain(AppComponent component){
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
