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
        addComponentsTo(newPanel);
        visitor.addVisitorPanel(newPanel);
        return newPanel;
    }

    private void addComponentsTo(SwingPanel panel){
        List<SwingComponent> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(comp -> System.out.println("abs comp: "+comp.getName()));
            componentList.forEach(component -> addParent(panel, component));
        }
    }

    private void addParent(SwingPanel panel, SwingComponent component) {
        JComponent jComponent = component.getParent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingPanel panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
