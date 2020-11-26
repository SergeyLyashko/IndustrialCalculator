package view.view.calculator;

import view.controller.ViewController;
import view.view.AppComponent;
import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;

class Message implements AppComponent, Host {

    private final JLabel jLabel;

    private static final String DEFAULT_VIEW = "test";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 140;
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;

    Message(ViewController viewController){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitMessageLabel(this);
    }
}
