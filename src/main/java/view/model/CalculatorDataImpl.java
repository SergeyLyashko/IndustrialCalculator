package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CalculatorDataImpl implements CalculatorData {

    private final List<AppComponent> componentsData;
    private final Queue<String> dataList;
    private boolean isArea;
    private boolean isWidth;

    public CalculatorDataImpl() {
        componentsData = new ArrayList<>();
        dataList = new LinkedList<>();
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
    public void setWidthStatus(boolean status) {
        this.isWidth = status;
    }

    @Override
    public void keyActionUpdate(){
        if(isWidth && !isArea){
            componentsData.forEach(this::update);
        }else{
            update(componentsData.get(1));
        }
    }

    private void update(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        String text = parent.getText();
        System.out.println("test double DATA: "+text);
        dataList.add(text);
    }

    @Override
    public Queue<String> getDataList(){
        return dataList;
    }

    @Override
    public void setAreaStatus(boolean status){
        this.isArea = status;
    }
}
