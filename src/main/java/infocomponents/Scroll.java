package infocomponents;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class Scroll implements SwingComponent {

    private final JScrollPane scrollPane = new JScrollPane();
    private Container container;

    SwingComponent createContainer(SwingComponent content, Visitor visitor){
        JViewport viewport = scrollPane.getViewport();
        viewport.add(content.getParent());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        container = scrollPane;
        visitor.addVisitorComponent(this);
        return this;
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
    public LayoutManager getLayout() {
        return null;
    }

    @Override
    public String getBorderLayout() {
        return null;
    }

    @Override
    public Container getParent() {
        return container;
    }

    @Override
    public void setParent(JComponent jPanel) {

    }
}
