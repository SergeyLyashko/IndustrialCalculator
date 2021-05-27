package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import viewcomponents.common.ViewController;
import viewcomponents.common.AppComponent;
import viewcomponents.common.Host;
import viewcomponents.common.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

@Component
class AreaCheckBox implements AppComponent, Host {

    private static final String TOOL_TIP_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
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

    public AreaCheckBox(int locationX, int locationY){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(false);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        jCheckBox.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        setFont();
        colorVisitor.addHost(this);
        addItemListener(viewController);
    }

    private void setFont(){
        Font deriveFont = jCheckBox.getFont().deriveFont(10f);
        jCheckBox.setFont(deriveFont);
    }

    private void addItemListener(ViewController viewController){
        jCheckBox.addItemListener(event ->
                viewController.areaCheckBoxState(event.getStateChange() == ItemEvent.SELECTED));
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return jCheckBox;
    }
}
