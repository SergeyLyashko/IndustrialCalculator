package view.calculatorcomponents;

import view.viewcontroller.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;

class Message implements AppComponent, Host {

    private static final String EMPTY = "";
    private final JLabel jLabel;

    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 140;
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;

    Message(ViewController viewController){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewController.setMessageComponent(this);
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitServiceLabel(this);
    }

    @Override
    public String getName() {
        return EMPTY;
    }
}
