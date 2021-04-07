package viewmodel;

import view.Visitor;
import view.AppComponent;
import viewcontroller.LabelBehavior;

import javax.swing.*;

class LabelBehaviorImpl implements LabelBehavior {

    private final Visitor colorVisitor;
    private final AppComponent component;
    private final JLabel label;

    LabelBehaviorImpl(Visitor colorVisitor, AppComponent component) {
        this.component = component;
        label = (JLabel) component.getComponentParent();
        this.colorVisitor = colorVisitor;
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
