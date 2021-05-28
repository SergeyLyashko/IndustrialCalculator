package components.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import components.common.ViewController;
import components.common.AppComponent;
import components.common.Host;
import components.common.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ItemEvent;

@Component("toolTipsBox")
public class ToolTipsCheckBox extends JCheckBox implements AppComponent, Host {

    private static final String BOX_NAME = "включить всплывающие подсказки";
    private static final String TOOL_TIP_TEXT = "включение/отключение всплывающих подсказок";
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    @Autowired
    private Visitor colorVisitor;

    @Autowired
    private ViewController viewController;

    public ToolTipsCheckBox(int locationX, int locationY){
        super.setSelected(true);
        super.setSize(WIDTH, HEIGHT);
        super.setText(BOX_NAME);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        colorVisitor.addHost(this);
        viewController.setToolTipState(this.isSelected());
        addItemListener();
    }

    private void addItemListener(){
        super.addItemListener(event -> viewController
                .setToolTipState(event.getStateChange() == ItemEvent.SELECTED));
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
