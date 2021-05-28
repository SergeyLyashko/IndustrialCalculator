package components.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import components.common.AppComponent;
import components.common.Host;
import components.common.Visitor;
import controller.LabelBehavior;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("result")
public class Result extends JLabel implements AppComponent, Host, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 6;
    private static final String DEFAULT_VIEW = "0.0 кг";
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;

    @Autowired
    private Visitor colorVisitor;

    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior labelBehavior;

    @PostConstruct
    private void afterPropertiesSet() {
        labelBehavior.setComponent(this);
        colorVisitor.addHost(this);
    }

    public Result(int locationX, int locationY){
        super.setSize(SIZE_X, SIZE_Y);
        super.setVisible(true);
        super.setText(DEFAULT_VIEW);
        super.setHorizontalAlignment(SwingConstants.RIGHT);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public String getName() {
        return DEFAULT_VIEW;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitServiceLabel(this);
    }

    @Override
    public int getFocusedRate() {
        return FOCUSED_RATE;
    }

    @Override
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}
