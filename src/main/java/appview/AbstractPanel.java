package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractPanel {

    private final JPanel jPanel = new JPanel();

    public abstract SwingPanel createPanel(String type, Visitor visitor);

    public SwingPanel order(String type, Visitor visitor){
        SwingPanel newPanel = createPanel(type, visitor);
        setLayout(newPanel);
        newPanel.setParent(jPanel);

        SwingPanel thisPanel = newPanel.getPanel(visitor);
        addComponentsTo(thisPanel);

        visitor.addVisitorComponent(newPanel);
        return thisPanel;
    }

    private void addComponentsTo(SwingPanel panel){
        List<SwingPanel> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(component -> addComponent(panel, component));
        }
    }

    private void addComponent(SwingPanel panel, SwingPanel component) {
        JComponent jComponent = component.getParent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingPanel panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
