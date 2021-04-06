package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("message")
class Message implements AppComponent, Host {

    private static final String EMPTY = "";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 140;
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;
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
        viewController.setMessageComponent(this);
        colorVisitor.addHost(this);
    }

    Message(){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
