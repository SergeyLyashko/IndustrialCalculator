package viewcomponents.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

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

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    public ToolTipsCheckBox(int locationX, int locationY){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        jCheckBox.setLocation(locationX, locationY);
    }

    @Override
    public void addController(ViewController viewController) {
        colorVisitor.addHost(this);
        addItemListener(viewController);
        checkBoxStateChecked(viewController);
    }

    private void checkBoxStateChecked(ViewController viewController){
        viewController.setToolTipState(jCheckBox.isSelected());
    }

    private void addItemListener(ViewController viewController){
        jCheckBox.addItemListener(event -> viewController.setToolTipState(event.getStateChange() == ItemEvent.SELECTED));
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
