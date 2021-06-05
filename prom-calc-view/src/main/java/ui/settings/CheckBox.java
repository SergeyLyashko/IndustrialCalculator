package ui.settings;

import controller.ViewController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.Host;
import ui.Visitor;

import javax.swing.*;
import java.awt.event.ItemEvent;

@Component
@Scope("prototype")
public class CheckBox extends JCheckBox implements UiComponent, Host {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public CheckBox(String boxTitle, String toolTipText, int locationX, int locationY, Visitor colorVisitor){
        super(boxTitle);
        super.setSelected(true);
        super.setSize(WIDTH, HEIGHT);
        super.setToolTipText(toolTipText);
        super.setLocation(locationX, locationY);
        colorVisitor.addHost(this);
        if(super.isSelected()){
            colorVisitor.activate();
        }else {
            colorVisitor.deactivate();
        }
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    public enum TypeBox {
        COLOR_THEME {
            @Override
            void addItemListener(CheckBox checkBox, ViewController viewController, Visitor colorVisitor) {
                checkBox.addItemListener(event -> {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        colorVisitor.activate();
                    } else {
                        colorVisitor.deactivate();
                    }
                    colorVisitor.raid();
                });
            }
        },
        TOOL_TIPS {
            @Override
            void addItemListener(CheckBox checkBox, ViewController viewController, Visitor visitor) {
                checkBox.addItemListener(event -> viewController
                        .setToolTipState(event.getStateChange() == ItemEvent.SELECTED));
            }
        },
        AREA{
            @Override
            void addItemListener(CheckBox checkBox, ViewController viewController, Visitor visitor) {
                checkBox.addItemListener(event ->
                        viewController.areaCheckBoxState(event.getStateChange() == ItemEvent.SELECTED));
            }
        };
        abstract void addItemListener(CheckBox checkBox, ViewController viewController, Visitor visitor);
    }
}
