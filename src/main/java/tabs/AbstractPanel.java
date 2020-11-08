package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

abstract class AbstractPanel {

    private final JPanel jPanel = new JPanel();

    abstract SwingComponent createPanel();

    SwingComponent order(Visitor visitor){
        SwingComponent newPanel = createPanel();
        newPanel.setParent(jPanel);
        setLayout(newPanel);
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
        Container panelParent = panel.getParent();
        String borderLayout = panel.getBorderLayout();
        panelParent.add(parentComponent, borderLayout);
    }

    private void setLayout(SwingComponent panel) {
        LayoutManager layout = panel.getLayout();
        Container parent = panel.getParent();
        parent.setLayout(layout);
    }
}
