package ui.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class AppPanel extends JPanel implements Colorizeble {

    private ColorChanger colorColorChanger;

    @PostConstruct
    private void afterPropertiesSet() {
        colorColorChanger.addColorizebleComponent(this);
    }

    @Autowired
    public void setColorVisitor(ColorChanger colorColorChanger){
        this.colorColorChanger = colorColorChanger;
    }

    public AppPanel(){
        super.setLayout(null);
    }

    @Override
    public void acceptVisitor(ColorChanger colorChanger) {
        colorChanger.changeComponentColor(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
