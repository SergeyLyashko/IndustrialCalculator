package viewcomponents.settings;

import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.Serializable;

class ColorThemeCheckBox implements AppComponent, Host, Serializable {

    private static final long serialVersionUID = 1L;

    private final JCheckBox jCheckBox;

    private static final String BOX_NAME = "темная тема оформления";
    private static final String TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 35;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    ColorThemeCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
    }

    @Override
    public void addController(ViewController viewController) {
        addHost(viewController);
        addItemListener(viewController);
        checkBoxStateChecked(viewController);
    }

    private void checkBoxStateChecked(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        if(jCheckBox.isSelected()){
            visitor.activate();
        }else {
            visitor.deactivate();
        }
    }

    private void addHost(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
    }

    private void addItemListener(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        jCheckBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                visitor.activate();
            } else {
                visitor.deactivate();
            }
            visitor.raid();
        });
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
