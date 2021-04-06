package viewcomponents.calculator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

@Component("areaCheckBox")
class AreaCheckBox implements AppComponent, Host, InitializingBean {

    private static final String TOOL_TIP_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
    private static final int LOCATION_X = 187;
    private static final int LOCATION_Y = 85;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;
    private final JCheckBox jCheckBox;

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

    AreaCheckBox(/*ViewController viewController*/){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(false);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        setFont();
        //addHost(viewController);
        //addItemListener(viewController);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addHost(viewController);
        addItemListener(viewController);
    }

    private void setFont(){
        Font deriveFont = jCheckBox.getFont().deriveFont(10f);
        jCheckBox.setFont(deriveFont);
    }

    private void addHost(ViewController viewController){
        //Visitor visitor = viewController.getVisitor();
        colorVisitor.addHost(this);
    }

    private void addItemListener(ViewController viewController){
        jCheckBox.addItemListener(event ->
                viewController.areaCheckBoxState(event.getStateChange() == ItemEvent.SELECTED));
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getParent() {
        return jCheckBox;
    }
}
