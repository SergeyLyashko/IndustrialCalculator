package appview;

import info.InfoComponent;
import settings.SettingsComponents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelBuilder implements SwingPanel {

    private JComponent jComponent;
    private LayoutManager layoutManager;
    private String borderLayout;
    private String panelName;
    private List<SwingComponent> componentList;

    public SwingPanel build(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingPanel createPanel(String type, Visitor visitor) {
                return create(type, visitor);
            }
        };
        return abstractPanel.order(type, visitor);
    }

    private SwingPanel create(String type, Visitor visitor) {
        switch (type){
            case "Калькулятор":
                return getCalculatorPanel(visitor);
            case "Настройки":
                return getSettingsPanel(visitor);
            case "Справка":
                return getInfoPanel(visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public List<SwingComponent> getComponents() {
        return componentList;
    }

    private SwingPanel getSettingsPanel(Visitor visitor){
        panelName = "Настройки";
        componentList = createSettingsComponents(visitor);
        return this;
    }

    private List<SwingComponent> createSettingsComponents(Visitor visitor){
        SettingsComponents settingsComponent = new SettingsComponents();
        return settingsComponent.getComponents(visitor);
    }

    private SwingPanel getCalculatorPanel(Visitor visitor){
        panelName = "Калькулятор";
        return this;
    }

    private SwingPanel getInfoPanel(Visitor visitor) {
        layoutManager = new BorderLayout();
        borderLayout = BorderLayout.CENTER;
        panelName = "Справка";
        componentList = createInfoComponents(visitor);
        return this;
    }

    private List<SwingComponent> createInfoComponents(Visitor visitor){
        InfoComponent infoComponent = new InfoComponent();
        return infoComponent.getComponents(visitor);
    }

    @Override
    public String getName() {
        return panelName;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return layoutManager;
    }

    public String getBorderLayout(){
        return borderLayout;
    }

    @Override
    public void setParent(JComponent jPanel) {
        this.jComponent = jPanel;
    }

    public JComponent getParent() {
        return jComponent;
    }
}
