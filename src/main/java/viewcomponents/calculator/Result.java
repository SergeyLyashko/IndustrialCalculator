package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("result")
class Result implements AppComponent, Host, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 6;
    private static final String DEFAULT_VIEW = "0.0 кг";
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 105;
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;
    private final JLabel jLabel;

    private ViewController viewController;
    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        viewController.setResultComponent(this);
        colorVisitor.addHost(this);
    }

    Result(){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public int getLocationX() {
        return LOCATION_X;
    }

    @Override
    public int getLocationY() {
        return LOCATION_Y;
    }

    @Override
    public JComponent getParent() {
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
