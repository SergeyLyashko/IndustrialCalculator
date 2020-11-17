package view.info;

import view.AppComponent;
import view.Host;
import view.Visitor;

import javax.swing.*;
import java.awt.*;

class ScrollWrapper implements AppComponent, Host {

    private final JScrollPane scrollPane;

    ScrollWrapper(){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
    }

    AppComponent add(AppComponent content){
        JViewport viewport = scrollPane.getViewport();
        viewport.add(content.getParent());
        return this;
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
        visitor.visit(this);
    }

    @Override
    public JComponent getParent() {
        return scrollPane;
    }

    @Override
    public void registerHost(Visitor visitor) {
        visitor.addHost(this);
    }
}
