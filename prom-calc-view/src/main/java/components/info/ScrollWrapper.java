package components.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import components.common.AppComponent;
import components.common.Host;
import components.common.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("scroller")
public class ScrollWrapper extends JScrollPane implements AppComponent, Host {

    private JViewport viewport;

    @Autowired
    private Visitor colorVisitor;

    @Autowired
    @Qualifier("info")
    private AppComponent info;

    public ScrollWrapper(){
        super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        super.setSize(new Dimension(350, 165));
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
        wrap(info);
    }

    private void wrap(AppComponent content){
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
