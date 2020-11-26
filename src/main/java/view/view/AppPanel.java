package view.view;

import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class AppPanel implements Host {

    private final JPanel jPanel = new JPanel();

    AppPanel(List<AppComponent> components, Visitor visitor){
        jPanel.setLayout(null);
        components.forEach(this::add);
        visitor.addHost(this);
    }

    private void add(AppComponent component) {
        Container parentComponent = component.getParent();
        jPanel.add(parentComponent);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getParent() {
        return jPanel;
    }
}
