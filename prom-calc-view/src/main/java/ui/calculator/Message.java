package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.AppComponent;
import ui.Host;
import ui.Visitor;
import controller.LabelBehavior;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("message")
public class Message extends JLabel implements AppComponent, Host {

    private static final String EMPTY = "";
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;

    @Autowired
    private Visitor colorVisitor;

    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior labelBehavior;

    @PostConstruct
    private void afterPropertiesSet() {
        labelBehavior.setComponent(this);
        colorVisitor.addHost(this);
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitServiceLabel(this);
    }

    @Override
    public String getName() {
        return EMPTY;
    }
}
