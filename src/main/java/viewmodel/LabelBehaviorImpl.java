package viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewcomponents.common.Visitor;
import viewcomponents.common.AppComponent;
import viewcontroller.LabelBehavior;

import javax.swing.*;

@Service("labelBehavior")
public class LabelBehaviorImpl implements LabelBehavior {

    private Visitor colorVisitor;
    private AppComponent component;
    private JLabel label;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @Override
    public void setComponent(AppComponent component){
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
