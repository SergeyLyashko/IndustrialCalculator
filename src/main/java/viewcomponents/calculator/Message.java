package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import viewcomponents.common.AppComponent;
import viewcomponents.common.Host;
import viewcomponents.common.Visitor;
import viewcontroller.LabelBehavior;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("message")
public class Message implements AppComponent, Host {

    private static final String EMPTY = "";
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;
    private final JLabel jLabel;
    private Visitor colorVisitor;
    private LabelBehavior labelBehavior;

    @Autowired
    @Qualifier("messageBehavior")
    public void setLabelBehavior(LabelBehavior labelBehavior){
        this.labelBehavior = labelBehavior;
    }

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @PostConstruct
    private void afterPropertiesSet() {
        labelBehavior.setComponent(this);
        colorVisitor.addHost(this);
    }

    public Message(int locationX, int locationY){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return jLabel;
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
