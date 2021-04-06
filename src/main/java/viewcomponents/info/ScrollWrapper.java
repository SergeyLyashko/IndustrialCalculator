package viewcomponents.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component("scroller")
public class ScrollWrapper implements AppComponent, Host {

    private final JScrollPane scrollPane;
    private JViewport viewport;
    private Visitor colorVisitor;
    private AppComponent info;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @Autowired
    @Qualifier("info")
    public void setInfo(AppComponent info){
        this.info = info;
    }

    public ScrollWrapper(){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
        wrap(info);
    }

    private void wrap(AppComponent content){
        viewport = scrollPane.getViewport();
        viewport.add(content.getParent());
    }

    @Override
    public int getLocationX() {
        return 0;
    }

    @Override
    public int getLocationY() {
        return 0;
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
    public JComponent getParent() {
        return scrollPane;
    }
}
