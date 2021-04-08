package viewcomponents.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.Serializable;

@Component("toolTipsBox")
public class ToolTipsCheckBox implements AppComponent, Host, Serializable {

    private static final long serialVersionUID = 1L;

    private final JCheckBox jCheckBox;
    private static final String BOX_NAME = "включить всплывающие подсказки";
    private static final String TOOL_TIP_TEXT = "включение/отключение всплывающих подсказок";
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;
    private transient Visitor colorVisitor;
    private ViewController viewController;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    public ToolTipsCheckBox(int locationX, int locationY){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        jCheckBox.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        colorVisitor.addHost(this);
        viewController.setToolTipState(jCheckBox.isSelected());
        addItemListener();
    }

    private void addItemListener(){
        jCheckBox.addItemListener(event -> viewController
                .setToolTipState(event.getStateChange() == ItemEvent.SELECTED));
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
