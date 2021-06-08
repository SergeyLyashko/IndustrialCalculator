package ui.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.Colorizeble;
import ui.ColorChanger;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("scroller")
class ScrollWrapper extends JScrollPane implements UiComponent, Colorizeble {

    private JViewport viewport;

    @Autowired
    private ColorChanger colorColorChanger;

    @Autowired
    @Qualifier("info")
    private UiComponent info;

    ScrollWrapper(){
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        super.setSize(new Dimension(368, 173));
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorColorChanger.addColorizebleComponent(this);
        wrap(info);
    }

    private void wrap(UiComponent content){
        viewport = super.getViewport();
        viewport.add(content.getComponentParent());
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeScrollColor(this);
    }

    @Override
    public JComponent getScrollViewPort(){
        return viewport;
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
