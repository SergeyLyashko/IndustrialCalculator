package viewcomponents.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.Serializable;

@Component("colorThemeBox")
class ColorThemeCheckBox implements AppComponent, Host, Serializable {

    private static final long serialVersionUID = 1L;

    private final JCheckBox jCheckBox;
    private transient Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    private static final String BOX_NAME = "темная тема оформления";
    private static final String TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    ColorThemeCheckBox(int locationX, int locationY){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        jCheckBox.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
        addItemListener();
        checkBoxStateChecked();
    }

    private void checkBoxStateChecked(){
        if(jCheckBox.isSelected()){
            colorVisitor.activate();
        }else {
            colorVisitor.deactivate();
        }
    }

    private void addItemListener(){
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return jCheckBox;
    }
}
