package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("dimension")
@Scope("prototype")
class DimensionLabel implements AppComponent, Host {

    private static final String DEFAULT_VIEW = "mm";
    private static final int SIZE_X = 25;
    private static final int SIZE_Y = 20;
    private final JLabel jLabel;
    private int locationX;
    private int locationY;
    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
    }

    DimensionLabel(){
        jLabel = new JLabel();
        jLabel.setSize(SIZE_X, SIZE_Y);
        jLabel.setVisible(true);
        jLabel.setText(DEFAULT_VIEW);
    }

    @Override
    public void setLocation(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
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
