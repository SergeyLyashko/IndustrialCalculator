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
        newPanel.setParentComponent(jPanel);
        addComponentsTo(newPanel, visitor);
        visitor.addVisitorComponent(newPanel);
        return newPanel;
    }

    private void addComponentsTo(SwingPanel panel, Visitor visitor){
        List<SwingPanel> componentList = panel.getComponents(visitor);
        if(!componentList.isEmpty()){
            componentList.forEach(component -> addComponent(panel, component));
        }
    }

    private void addComponent(SwingPanel panel, SwingPanel component) {
        JComponent jComponent = component.getParentsComponent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingPanel panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
