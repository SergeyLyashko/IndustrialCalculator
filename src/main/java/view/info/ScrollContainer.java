package view.info;

import view.Integrator;
import view.AppComponent;
import view.Visitor;

import javax.swing.*;
import java.awt.*;

class ScrollContainer implements AppComponent {

    private final JScrollPane scrollPane;

    ScrollContainer(){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
    }

    AppComponent add(AppComponent content, Visitor visitor){
        JViewport viewport = scrollPane.getViewport();
        viewport.add(content.getParent());
        visitor.addHost(this);
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
        visitor.addHost(this);
    }

    @Override
    public JComponent getParent() {
        return scrollPane;
    }

    @Override
    public Integrator getIntegrator() {
        return this::add;
    }

    @Override
    public void addListener(AppComponent component, Visitor visitor) {

    }

}
