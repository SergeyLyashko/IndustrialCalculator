package view.model;

import view.view.Visitor;
import view.view.AppComponent;

import javax.swing.*;

class LabelBehaviorImpl {

    private final Visitor colorVisitor;
    private final AppComponent component;

    LabelBehaviorImpl(Visitor colorVisitor, AppComponent component) {
        this.component = component;
        this.colorVisitor = colorVisitor;
    }

    void show(String text, boolean alert) {
        if(component != null) {
            JLabel parent = (JLabel) component.getParent();
            parent.setText(text);
            if(alert) {
                colorVisitor.alert(component);
            }
        }
    }

    void reset(){
        if(component != null){
            JLabel parent = (JLabel) component.getParent();
            parent.setText(component.getName());
            colorVisitor.reset(component);
        }
    }
}
