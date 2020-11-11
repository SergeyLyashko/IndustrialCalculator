package infocomponents;

import appcomponents.AbstractFactory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class ScrollContainer implements SwingComponent {

    private final JScrollPane scrollPane = new JScrollPane();
    private Container container;

    SwingComponent add(SwingComponent content, Visitor visitor){
        JViewport viewport = scrollPane.getViewport();
        viewport.add(content.getParent());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(350, 165));
        container = scrollPane;
        visitor.addVisitorComponent(this);
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
    public String getName() {
        return null;
    }

    @Override
    public List<SwingComponent> getComponents() {
        return null;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.addVisitorComponent(this);
    }

    @Override
    public Container getParent() {
        return container;
    }

    @Override
    public void setParent(JComponent jComponent) {
    }

    @Override
    public AbstractFactory getFactory() {
        return this::add;
    }
}
