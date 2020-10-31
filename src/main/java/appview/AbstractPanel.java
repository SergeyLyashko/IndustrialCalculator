package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractPanel {

    private final JPanel jPanel = new JPanel();

    public abstract SwingComponent createPanel(String type, Visitor visitor);

    public SwingComponent order(String type, Visitor visitor){
        SwingComponent panel = createPanel(type, visitor);
        setLayout(panel);
        visitor.addVisitorComponent(panel);

        List<SwingComponent> componentList = panel.getComponents(visitor);
        componentList.forEach(component -> setBorderLayout(panel, component));
        return panel;
    }

    private void setBorderLayout(SwingComponent panel, SwingComponent component) {
        JComponent jComponent = component.getParentsComponent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingComponent panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }

    public JPanel getAbstractComponent(){
        return jPanel;
    }
}
