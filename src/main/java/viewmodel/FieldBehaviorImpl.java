package viewmodel;

import view.AppComponent;
import viewcontroller.FieldBehavior;

import javax.swing.*;
import java.awt.*;

class FieldBehaviorImpl implements FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final JFormattedTextField textField;
    private final String name;

    FieldBehaviorImpl(AppComponent component){
        name = component.getName();
        textField = (JFormattedTextField) component.getParent();
    }

    @Override
    public void fieldActivate() {
        textField.setText(name);
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
    }

    @Override
    public void areaActivate(){
        textField.setText(BOX_NAME_AREA);
    }

    @Override
    public void areaDeactivate(){
        textField.setText(name);
    }

    @Override
    public void fieldDeactivate() {
        textField.setText(name);
        textField.setEditable(false);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.LIGHT_GRAY);
    }
}
