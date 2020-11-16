package view.calculator.textlabels;

import view.AppComponent;
import view.Visitor;

import javax.swing.*;

public class Message implements AppComponent {

    private final JLabel jLabel;

    private static final String DEFAULT_VIEW = "test";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 140;
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;

    public Message(){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void addListener(Visitor visitor) {

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
        visitor.visit(this);
    }
}
