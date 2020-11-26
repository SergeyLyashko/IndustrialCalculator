package view.view.calculator;

import view.view.AppComponent;
import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;

class Result implements AppComponent, Host {

    private final JLabel jLabel;

    private static final String DEFAULT_VIEW = "0.0";
    private static final String KG = "кг";
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 105;
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;

    Result(){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public void registerAsHost(Visitor visitor) {
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitResultLabel(this);
    }
}
