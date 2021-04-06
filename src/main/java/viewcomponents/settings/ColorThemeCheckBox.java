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

@Component("colorThemeBox")
public class ColorThemeCheckBox implements AppComponent, Host, Serializable {

    private static final long serialVersionUID = 1L;

    private final JCheckBox jCheckBox;
    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    private static final String BOX_NAME = "темная тема оформления";
    private static final String TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 35;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public ColorThemeCheckBox(){
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
        //Visitor colorVisitor = viewController.getVisitor();
        if(jCheckBox.isSelected()){
            colorVisitor.activate();
        }else {
            colorVisitor.deactivate();
        }
    }

    private void addHost(ViewController viewController){
        //Visitor visitor = viewController.getVisitor();
        colorVisitor.addHost(this);
    }

    private void addItemListener(ViewController viewController){
        //Visitor colorVisitor = viewController.getVisitor();
        jCheckBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                colorVisitor.activate();
            } else {
                colorVisitor.deactivate();
            }
            colorVisitor.raid();
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
