package ui.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
class AppPanel extends JPanel implements Colorizeble {

    @Autowired
    private ColorChanger colorColorChanger;

    @PostConstruct
    private void afterPropertiesSet() {
        colorColorChanger.addColorizebleComponent(this);
    }

    AppPanel(){
        super.setLayout(null);
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeComponentColor(this);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
