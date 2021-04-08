package viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import view.AppComponent;
import viewcontroller.FieldBehavior;

import javax.swing.*;
import java.awt.*;

@Service("fieldBehavior")
@Scope("prototype")
public class FieldBehaviorImpl implements FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private JFormattedTextField textField;
    private String name;

    @Override
    public void setComponent(AppComponent component){
        this.name = component.getName();
        textField = (JFormattedTextField) component.getComponentParent();
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
