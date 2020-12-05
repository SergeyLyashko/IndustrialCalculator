package view.model.behavior;

import view.view.AppComponent;

import javax.swing.*;

public class LabelBehavior {

    private AppComponent component;

    public void show(String result) {
        if(component != null) {
            JLabel parent = (JLabel) component.getParent();
            parent.setText(result);
        }
    }

    public void reset(){
        if(component != null){
            JLabel parent = (JLabel) component.getParent();
            parent.setText(component.getName());
        }
    }

    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
