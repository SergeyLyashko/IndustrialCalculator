package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractPanel {

    private final JPanel jPanel = new JPanel();

    public abstract SwingPanel createPanel(String type, Visitor visitor);

    public SwingPanel order(String type, Visitor visitor){
        SwingPanel panel = createPanel(type, visitor);
        setLayout(panel);
        panel.setParentComponent(jPanel);

        List<SwingPanel> componentList = panel.getComponents(visitor);
        componentList.forEach(component -> setBorderLayout(panel, component));
        visitor.addVisitorComponent(panel);
        return panel;
    }

    private void setBorderLayout(SwingPanel panel, SwingPanel component) {
        JComponent jComponent = component.getParentsComponent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingPanel panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }
}
