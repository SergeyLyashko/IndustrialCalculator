package ui.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.UiComponent;
import ui.Host;
import ui.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("scroller")
public class ScrollWrapper extends JScrollPane implements UiComponent, Host {

    private JViewport viewport;

    @Autowired
    private Visitor colorVisitor;

    @Autowired
    @Qualifier("info")
    private UiComponent info;

    public ScrollWrapper(){
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        super.setSize(new Dimension(368, 173));
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
        wrap(info);
    }

    private void wrap(UiComponent content){
        viewport = super.getViewport();
        viewport.add(content.getComponentParent());
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitScroll(this);
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
