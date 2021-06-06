package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.annotation.PostConstruct;
import javax.swing.*;
/*
@Component("dimension")
@Scope("prototype")
public class DimensionLabel extends JLabel implements UiComponent, Colorizeble {

    private static final String DEFAULT_VIEW = "mm";
    private static final int SIZE_X = 25;
    private static final int SIZE_Y = 20;
    @Autowired
    private ColorChanger colorChanger;

    @PostConstruct
    private void afterPropertiesSet() {
        colorChanger.addColorizebleComponent(this);
    }

    public DimensionLabel(int locationX, int locationY){
        super.setSize(SIZE_X, SIZE_Y);
        super.setVisible(true);
        super.setText(DEFAULT_VIEW);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeLabelColor(this);
    }
}
*/