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
        System.out.println("abs newPanel: "+newPanel.getName());

        // TODO getPanel вызывается для создания панели Tab
        SwingPanel thisPanel = newPanel.getPanel(visitor);
        System.out.println("abs thisPanel: "+thisPanel.getName());
        addComponentsTo(thisPanel);

        visitor.addVisitorPanel(newPanel);
        return thisPanel;
    }

    private void addComponentsTo(SwingPanel panel){
        List<SwingComponent> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(comp -> System.out.println("abs: "+comp.getName()));
            componentList.forEach(component -> addComponent(panel, component));
        }
    }

    private void addComponent(SwingPanel panel, SwingComponent component) {
        JComponent jComponent = component.getParent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingPanel panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
