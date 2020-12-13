package viewmodel;

import view.Host;
import view.Visitor;
import view.AppComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ColorChangeVisitor implements Visitor {

    private final List<Host> componentsList = new CopyOnWriteArrayList<>();
    private Color backGround;
    private Color foreGround;
    private Color markerColor;
    private Color serviceStringColor;
    private final Color alertColor = Color.RED;

    private void raid() {
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addHost(Host host) {
        componentsList.add(host);
    }

    @Override
    public void visitComponent(Host host) {
        JComponent parent = host.getParent();
        parent.setBackground(backGround);
        parent.setForeground(foreGround);
    }

    @Override
    public void visitServiceLabel(Host host) {
        JComponent parent = host.getParent();
        parent.setForeground(serviceStringColor);
    }

    @Override
    public void visitLabel(Host host) {
        JComponent parent = host.getParent();
        parent.setForeground(markerColor);
    }

    @Override
    public void visitScroll(Host host) {
        JComponent scrollViewPort = host.getScrollViewPort();
        scrollViewPort.setBackground(backGround);
    }

    @Override
    public void alert(AppComponent component) {
        JComponent parent = component.getParent();
        parent.setForeground(alertColor);
    }

    @Override
    public void reset(AppComponent component) {
        JComponent parent = component.getParent();
        parent.setForeground(serviceStringColor);
    }

    @Override
    public void deactivate() {
        backGround = new Color(250, 236, 229);
        foreGround = Color.BLACK;
        markerColor = Color.BLACK;
        serviceStringColor = Color.BLUE;
        raid();
    }

    @Override
    public void activate() {
        backGround = Color.BLACK;
        foreGround = Color.WHITE;
        markerColor = Color.WHITE;
        serviceStringColor = Color.GREEN;
        raid();
    }
}
