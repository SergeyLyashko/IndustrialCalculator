package infocomponents;

import view.ViewController;
import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;
import java.awt.*;

class ScrollWrapper implements AppComponent, Host {

    private final JScrollPane scrollPane;
    private JViewport viewport;

    ScrollWrapper(ViewController viewController, AppComponent appComponent){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
        addHost(viewController);
        wrap(appComponent);
    }

    private void addHost(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
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
