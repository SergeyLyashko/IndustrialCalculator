package viewcomponents.info;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;
import java.awt.*;

@Component("scroller")
public class ScrollWrapper implements AppComponent, Host, InitializingBean {

    private final JScrollPane scrollPane;
    private JViewport viewport;
    private Visitor colorVisitor;
    private ViewController viewController;
    private AppComponent info;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @Autowired
    @Qualifier("info")
    public void setInfo(AppComponent info){
        this.info = info;
    }

    public ScrollWrapper(/*ViewController viewController, AppComponent appComponent*/){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
        //addHost(viewController);
        //wrap(appComponent);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addHost(viewController);
        wrap(info);
    }

    private void addHost(ViewController viewController){
        //Visitor visitor = viewController.getVisitor();
        colorVisitor.addHost(this);
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
