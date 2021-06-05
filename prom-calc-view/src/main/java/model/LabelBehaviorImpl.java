package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.ColorChanger;
import ui.UiComponent;
import controller.LabelBehavior;

import javax.swing.*;

@Service("labelBehavior")
public class LabelBehaviorImpl implements LabelBehavior {

    @Autowired
    private ColorChanger colorChanger;
    private UiComponent component;
    private JLabel label;

    @Override
    public void setComponent(UiComponent component){
        this.component = component;
        label = (JLabel) component.getComponentParent();
    }

    @Override
    public void show(String text, boolean alert) {
        if(component != null) {
            label.setText(text);
            if(alert) {
                colorChanger.setAlertColor(component);
            }
        }
    }

    @Override
    public void reset(){
        if(component != null){
            label.setText(component.getName());
            colorChanger.setDefaultColor(component);
        }
    }
}
