package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractPanel {

    private final JPanel jPanel = new JPanel();

    public abstract SwingComponent createPanel(String type, Visitor visitor);

    public SwingComponent order(String type, Visitor visitor){
        SwingComponent newPanel = createPanel(type, visitor);
        setLayout(newPanel);
        newPanel.setParent(jPanel);
        System.out.println("abs newPanel: "+newPanel.getName());//TEST
        addComponentsTo(newPanel);
        visitor.addVisitorComponent(newPanel);
        return newPanel;
    }

    private void addComponentsTo(SwingComponent panel){
        List<SwingComponent> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(comp -> System.out.println("abs comp: "+comp.getName()));//TEST
            componentList.forEach(component -> addParent(panel, component));
        }
    }

    private void addParent(SwingComponent panel, SwingComponent component) {
        Container parentComponent = component.getParent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(parentComponent, borderLayout);
    }

    private void setLayout(SwingComponent panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
