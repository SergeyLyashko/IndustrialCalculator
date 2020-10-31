package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

abstract class AbstractPanel {

    private final JPanel jPanel;
    private List<SwingComponent> componentList;

    AbstractPanel(){
        jPanel = new JPanel();
    }

    abstract SwingComponent createPanel(String type, Visitor visitor);

    void order(String type, Visitor visitor){
        SwingComponent panel = createPanel(type, visitor);
        setLayout(panel);

        componentList = panel.getComponents(visitor);
        componentList.forEach(component -> setBorderLayout(panel, component));
    }

    List<SwingComponent> getComponentsPanelList(){
        return componentList;
    }

    private void setBorderLayout(SwingComponent panel, SwingComponent component) {
        JComponent jComponent = component.getSwingComponent();
        String borderLayout = panel.getBorderLayout();
        jPanel.add(jComponent, borderLayout);
    }

    private void setLayout(SwingComponent panel) {
        LayoutManager layout = panel.getLayout();
        jPanel.setLayout(layout);
    }

    JPanel getComponentSwing(){
        return jPanel;
    }
}
