package model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.ColorChanger;
import model.LabelBehavior;

import javax.swing.*;

@Service("labelBehavior")
class LabelBehaviorImpl implements LabelBehavior {

    @Lazy
    @Autowired
    private ColorChanger colorChanger;
    private JLabel label;
    private JComponent component;

    @Override
    public void setComponent(JComponent component){
        this.component = component;
        label = (JLabel) component;
    }

    @Override
    public void show(String text, boolean alert) {
        label.setText(text);
        if(alert) {
            colorChanger.setAlertColor(label);
        }
    }

    @Override
    public void reset(){
        label.setText(component.getName());
        colorChanger.setDefaultColor(component);
    }
}
