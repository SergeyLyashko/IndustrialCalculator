package view.view.calculator;

import view.controller.ViewController;
import view.view.AppComponent;
import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;

class Result implements AppComponent, Host, Comparable<AppComponent>  {

    private final JLabel jLabel;

    private static final int FOCUSED_RATE = 6;
    private static final String DEFAULT_VIEW = "0.0";
    private static final String KG = " кг";
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 105;
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;

    Result(ViewController viewController){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW+KG);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        viewController.setResultComponent(this);
        addHost(viewController);
    }

    private void addHost(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
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
        return DEFAULT_VIEW+KG;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitResultLabel(this);
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
