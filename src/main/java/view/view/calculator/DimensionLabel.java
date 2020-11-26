package view.view.calculator;

import view.view.AppComponent;
import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;

class DimensionLabel implements AppComponent, Host {

    private static final String DEFAULT_VIEW = "mm";
    private static final int SIZE_X = 25;
    private static final int SIZE_Y = 20;
    private final JLabel jLabel;
    private final int locationX;
    private final int locationY;

    DimensionLabel(int locationX, int locationY){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        this.locationX = locationX;
        this.locationY = locationY;
    }

    @Override
    public void registerAsHost(Visitor visitor) {
        visitor.addHost(this);
    }

    @Override
    public int getLocationX() {
        return locationX;
    }

    @Override
    public int getLocationY() {
        return locationY;
    }

    @Override
    public JComponent getParent() {
        return jLabel;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitLabel(this);
    }

}
