package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface Panel {

    default JPanel getPanel(){
        return new JPanel();
    }

    SwingComponent create(String type, List<SwingComponent> components, Visitor visitor);

    default SwingComponent ordered(SwingComponent component, Visitor visitor){
        JPanel jPanel = getPanel();
        component.setParent(jPanel);
        setLayout(component);
        addComponentsTo(component);
        visitor.addVisitorComponent(component);
        return component;
    }

    default void addComponentsTo(SwingComponent panel){
        List<SwingComponent> componentList = panel.getComponents();
        if(componentList != null){
            componentList.forEach(comp -> System.out.println("abs panels components: "+comp.getName()));//TEST
            componentList.forEach(component -> addParent(panel, component));
        }
    }

    default void addParent(SwingComponent panel, SwingComponent component) {
        Container parentComponent = component.getParent();
        Container panelParent = panel.getParent();
        String borderLayout = panel.getBorderLayout();
        panelParent.add(parentComponent, borderLayout);
    }

    default void setLayout(SwingComponent panel) {
        LayoutManager layout = panel.getLayout();
        Container parent = panel.getParent();
        parent.setLayout(layout);
    }
}
