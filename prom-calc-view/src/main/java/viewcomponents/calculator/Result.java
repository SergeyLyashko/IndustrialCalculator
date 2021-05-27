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

@Component("result")
public class Result implements AppComponent, Host, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 6;
    private static final String DEFAULT_VIEW = "0.0 кг";
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;
    private final JLabel jLabel;
    private Visitor colorVisitor;

    private LabelBehavior labelBehavior;

    @Autowired
    @Qualifier("resultBehavior")
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

    public Result(int locationX, int locationY){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return jLabel;
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
