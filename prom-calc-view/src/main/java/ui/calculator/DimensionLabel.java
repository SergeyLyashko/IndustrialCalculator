package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.AppComponent;
import ui.Host;
import ui.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("dimension")
@Scope("prototype")
public class DimensionLabel extends JLabel implements AppComponent, Host {

    private static final String DEFAULT_VIEW = "mm";
    private static final int SIZE_X = 25;
    private static final int SIZE_Y = 20;
    @Autowired
    private Visitor colorVisitor;

    @PostConstruct
    private void afterPropertiesSet() {
        colorVisitor.addHost(this);
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitLabel(this);
    }
}
