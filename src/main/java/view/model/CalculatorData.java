package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculatorData implements CalculatorDataObserver, DataReceiver {

    private final List<AppComponent> componentsData;
    private final List<String> dataList;
    private boolean areaStatus;

    public CalculatorData() {
        componentsData = new LinkedList<>();
        dataList = new ArrayList<>();
    }

    @Override
    public void addData(AppComponent component){
        componentsData.add(component);
    }

    @Override
    public void addData(String data){
        dataList.add(data);
    }

    @Override
    public void keyActionUpdate(){
        componentsData.forEach(this::update);
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        System.out.println("test double DATA: "+text);
        dataList.add(text);
    }

    @Override
    public List<String> getDataList(){
        return dataList;
    }

    @Override
    public boolean isArea() {
        return areaStatus;
    }

    @Override
    public void setAreaStatus(boolean status){
        this.areaStatus = status;
    }
}
