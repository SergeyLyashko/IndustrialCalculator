package view.model.behavior;

import view.model.LabelBehavior;
import view.view.Visitor;
import view.view.AppComponent;

import javax.swing.*;

public class LabelBehaviorImpl implements LabelBehavior {

    private final Visitor colorVisitor;
    private final AppComponent component;

    public LabelBehaviorImpl(Visitor colorVisitor, AppComponent component) {
        this.component = component;
        this.colorVisitor = colorVisitor;
    }

    @Override
    public void show(String text, boolean alert) {
        if(component != null) {
            JLabel parent = (JLabel) component.getParent();
            parent.setText(text);
            if(alert) {
                colorVisitor.alert(component);
            }
        }
    }

    @Override
    public void reset(){
        if(component != null){
            JLabel parent = (JLabel) component.getParent();
            parent.setText(component.getName());
            colorVisitor.reset(component);
        }
    }
}
