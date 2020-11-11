package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface Panel {

    default JPanel getPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        return jPanel;
    }

    SwingComponent create(String type, List<SwingComponent> components, Visitor visitor);

    default SwingComponent ordered(SwingComponent component, Visitor visitor){
        JPanel jPanel = getPanel();
        component.setParent(jPanel);
        addComponentsTo(component);
        visitor.addVisitorComponent(component);
        return component;
    }

    default void addComponentsTo(SwingComponent panel){
        List<SwingComponent> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(comp -> System.out.println("abs panels components: "+comp.getName()));//TEST
            componentList.forEach(component -> add(panel, component));
        }
    }

    default void add(SwingComponent panel, SwingComponent component) {
        Container parentComponent = component.getParent();
        Container panelParent = panel.getParent();
        panelParent.add(parentComponent);
    }
}
