package model.impl;

import org.springframework.stereotype.Service;
import model.FieldBehavior;

import javax.swing.*;
import java.awt.*;

@Service("fieldBehavior")
class FieldBehaviorImpl implements FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";

    @Override
    public void fieldActivate(JComponent component) {
        if(component instanceof JFormattedTextField){
            String name = component.getName();
            JFormattedTextField textField = (JFormattedTextField) component;
            textField.setText(name);
            textField.setEditable(true);
            textField.setForeground(Color.GRAY);
            textField.setBackground(Color.white);
        }
    }

    @Override
    public void areaActivate(JComponent component){
        if(component instanceof JFormattedTextField){
            JFormattedTextField textField = (JFormattedTextField) component;
            textField.setText(BOX_NAME_AREA);
        }
    }

    @Override
    public void areaDeactivate(JComponent component){
        if(component instanceof JFormattedTextField){
            String name = component.getName();
            JFormattedTextField textField = (JFormattedTextField) component;
            textField.setText(name);
        }
    }

    @Override
    public void fieldDeactivate(JComponent component) {
        if(component instanceof JFormattedTextField){
            String name = component.getName();
            JFormattedTextField textField = (JFormattedTextField) component;
            textField.setText(name);
            textField.setEditable(false);
            textField.setForeground(Color.GRAY);
            textField.setBackground(Color.LIGHT_GRAY);
        }
    }
}
