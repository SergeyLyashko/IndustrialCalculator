package appview;

import javax.swing.*;
import java.util.List;

class TabbedPane implements SwingPanel {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    @Override
    public String getName() {
        return "tabbed pane";
    }

    @Override
    public SwingPanel getPanel(Visitor visitor) {
        SwingPanel calc = createNewPanel("Калькулятор", visitor);
        SwingPanel settings = createNewPanel("Настройки", visitor);
        SwingPanel info = createNewPanel("Справка", visitor);

        addToTab("Калькулятор", calc);
        addToTab("Настройки", settings);
        addToTab("Справка", info);

        return this;
    }
    private void addToTab(String type, SwingPanel component) {
        JComponent parentsComponent = component.getParent();
        tabbedPane.addTab(type, parentsComponent);
    }

    private SwingPanel createNewPanel(String type, Visitor visitor) {
        PanelBuilder panelBuilder = new PanelBuilder();
        switch (type){
            case "Калькулятор":
                return panelBuilder.build("Калькулятор", visitor);
            case "Настройки":
                return panelBuilder.build("Настройки", visitor);
            case "Справка":
                return panelBuilder.build("Справка", visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public List<SwingComponent> getComponents() {
        return null;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {}

    @Override
    public JComponent getParent() {
        return tabbedPane;
    }
}
