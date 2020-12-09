package view.model.behavior;

import view.controller.Visitor;
import view.view.AppComponent;

import javax.swing.*;

public class LabelBehavior {

    private final Visitor colorVisitor;
    private AppComponent component;

    public LabelBehavior(Visitor colorVisitor) {
        this.colorVisitor = colorVisitor;
    }

    public void show(String text, boolean alert) {
        if(component != null) {
            JLabel parent = (JLabel) component.getParent();
            parent.setText(text);
            if(alert) {
                colorVisitor.alertColor(component);
            }
        }
    }

    public void reset(){
        if(component != null){
            JLabel parent = (JLabel) component.getParent();
            parent.setText(component.getName());
            colorVisitor.resetColor(component);
        }
    }

    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
