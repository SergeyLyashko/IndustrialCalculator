package viewcomponents.calculator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;

@Component("dimension")
@Scope("prototype")
class DimensionLabel implements AppComponent, Host, InitializingBean {

    private static final String DEFAULT_VIEW = "mm";
    private static final int SIZE_X = 25;
    private static final int SIZE_Y = 20;
    private final JLabel jLabel;
    private int locationX;
    private int locationY;

    //private ViewController viewController;
    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    /*
    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }*/

    @Override
    public void afterPropertiesSet() throws Exception {
        //ddHost(viewController);
        colorVisitor.addHost(this);
    }

    DimensionLabel(/*ViewController viewController, int locationX, int locationY*/){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
        //this.locationX = locationX;
        //this.locationY = locationY;
        //addHost(viewController);
    }

    @Override
    public void setLocation(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }

    private void addHost(ViewController viewController){
        //Visitor visitor = viewController.getVisitor();
        colorVisitor.addHost(this);
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
