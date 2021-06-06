package ui.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Colorizeble;
import ui.ColorChanger;
import ui.UiComponent;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class AppPanel extends JPanel implements UiComponent, Colorizeble {

    @Autowired
    private ColorChanger colorColorChanger;

    @PostConstruct
    private void afterPropertiesSet() {
        colorColorChanger.addColorizebleComponent(this);
    }

    public AppPanel(){
        super.setLayout(null);
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeComponentColor(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
