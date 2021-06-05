package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.Colorizeble;
import ui.ColorChanger;
import controller.LabelBehavior;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("message")
public class Message extends JLabel implements UiComponent, Colorizeble {

    private static final String EMPTY = "";
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;

    @Autowired
    private ColorChanger colorChanger;

    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior labelBehavior;

    @PostConstruct
    private void afterPropertiesSet() {
        labelBehavior.setComponent(this);
        colorChanger.addColorizebleComponent(this);
    }

    public Message(int locationX, int locationY){
        super.setSize(SIZE_X, SIZE_Y);
        super.setVisible(true);
        super.setHorizontalAlignment(SwingConstants.CENTER);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public void acceptVisitor(ColorChanger colorChanger) {
        colorChanger.changeServiceLabelColor(this);
    }

    @Override
    public String getName() {
        return EMPTY;
    }
}
