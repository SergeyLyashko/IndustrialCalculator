package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.Visitor;
import ui.UiComponent;
import controller.LabelBehavior;

import javax.swing.*;

@Service("labelBehavior")
public class LabelBehaviorImpl implements LabelBehavior {

    @Autowired
    private Visitor colorVisitor;
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
                colorVisitor.alert(component);
            }
        }
    }

    @Override
    public void reset(){
        if(component != null){
            label.setText(component.getName());
            colorVisitor.reset(component);
        }
    }
}
