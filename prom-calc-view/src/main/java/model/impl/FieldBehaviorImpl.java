package model.impl;

import org.springframework.stereotype.Service;
import ui.UiComponent;
import model.FieldBehavior;

import javax.swing.*;
import java.awt.*;

@Service("fieldBehavior")
class FieldBehaviorImpl implements FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";

    @Override
    public void fieldActivate(UiComponent component) {
        String name = component.getName();
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        textField.setText(name);
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
    }

    @Override
    public void areaActivate(UiComponent component){
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        textField.setText(BOX_NAME_AREA);
    }

    @Override
    public void areaDeactivate(UiComponent component){
        String name = component.getName();
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        textField.setText(name);
    }

    @Override
    public void fieldDeactivate(UiComponent component) {
        String name = component.getName();
        JFormattedTextField textField = (JFormattedTextField) component.getComponentParent();
        textField.setText(name);
        textField.setEditable(false);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.LIGHT_GRAY);
    }
}
