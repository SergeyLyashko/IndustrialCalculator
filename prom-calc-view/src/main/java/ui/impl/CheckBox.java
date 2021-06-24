package ui.impl;

import controller.ViewController;
import model.DataManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * Check boxes in panels
 * @author Sergey Lyashko
 */
@Component
@Scope("prototype")
class CheckBox extends JCheckBox implements Colorizeble {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public CheckBox(String boxTitle, String toolTipText, int locationX, int locationY, ColorChanger colorChanger){
        super(boxTitle);
        super.setSelected(true);
        super.setSize(WIDTH, HEIGHT);
        super.setToolTipText(toolTipText);
        super.setLocation(locationX, locationY);
        colorChanger.addColorizebleComponent(this);
        if(super.isSelected()){
            colorChanger.activateDarkScheme();
        }else {
            colorChanger.activateLightScheme();
        }
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeComponentColor(this);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    public enum TypeBox {
        COLOR_THEME {
            @Override
            public void addItemListener(CheckBox checkBox, ViewController viewController, ColorChanger colorChanger, DataManager data) {
                checkBox.addItemListener(event -> {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        colorChanger.activateDarkScheme();
                    } else {
                        colorChanger.activateLightScheme();
                    }
                    colorChanger.componentsRecolor();
                });
            }
        },
        TOOL_TIPS {
            @Override
            public void addItemListener(CheckBox checkBox, ViewController viewController, ColorChanger colorChanger, DataManager data) {
                checkBox.addItemListener(event -> viewController.setToolTipState(event.getStateChange() == ItemEvent.SELECTED));
            }
        },
        AREA{
            @Override
            public void addItemListener(CheckBox checkBox, ViewController viewController, ColorChanger colorChanger, DataManager data) {
                checkBox.setSelected(false);
                checkBox.addItemListener(event -> {
                    data.setIsComplexArea(event.getStateChange() == ItemEvent.SELECTED);
                    viewController.action();
                });
            }
        };

        public abstract void addItemListener(CheckBox checkBox, ViewController viewController, ColorChanger colorChanger, DataManager dataManager);
    }
}
