package ui.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("scroller")
class ScrollWrapper extends JScrollPane implements Colorizeble {

    private JViewport viewport;
    @Autowired
    private ColorChanger colorColorChanger;
    @Autowired
    @Qualifier("info")
    private JComponent info;

    ScrollWrapper(){
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        super.setSize(new Dimension(368, 173));
    }

    @SuppressWarnings("unused")
    @PostConstruct
    private void afterPropertiesSet() {
        colorColorChanger.addColorizebleComponent(this);
        wrap(info);
    }

    private void wrap(JComponent component){
        viewport = super.getViewport();
        viewport.add(component);
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeComponentColor(viewport);
    }
}
