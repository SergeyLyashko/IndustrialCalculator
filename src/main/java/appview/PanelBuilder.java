package appview;

import info.ScrollPanel;
import settings.CheckBoxFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelBuilder implements SwingPanel {

    private JPanel jPanel;
    private LayoutManager layoutManager;
    private String borderLayout;
    private String panelName;
    private List<SwingPanel> componentList;

    // TODO del visitor
    public SwingPanel build(String type, Visitor visitor){
        return buildPanel(type, visitor);
    }

    private SwingPanel buildPanel(String type, Visitor visitor){
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
                return getCalculator(visitor);
            case "Настройки":
                return getSettings(visitor);
            case "Справка":
                return getInfo(visitor);
            case "Scroll":
                return new ScrollPanel();
            case "Common":
                return getCommon(visitor);
            case "Tabbed":
                return new Tabbed();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingPanel getCommon(Visitor visitor) {
        layoutManager = new GridLayout(1, 1);
        borderLayout = null;
        panelName = "Common_Builder";
        return create("Tabbed", visitor);
    }

    private SwingPanel getSettings(Visitor visitor){
        layoutManager = null;
        borderLayout = null;
        panelName = "Settings_Builder";
        componentList = new ArrayList<>();
        CheckBoxFactory checkBoxFactory = new CheckBoxFactory();
        SelectableCheckBox theme = checkBoxFactory.getCheckBox("theme", visitor);
        SelectableCheckBox toolTip = checkBoxFactory.getCheckBox("toolTip", visitor);
        componentList.add(theme);
        componentList.add(toolTip);
        return this;
    }

    private SwingPanel getCalculator(Visitor visitor){
        componentList = null;
        layoutManager = null;
        borderLayout = null;
        panelName = "Калькулятор_Builder";
        return this;
    }

    private SwingPanel getInfo(Visitor visitor) {
        componentList = null;
        layoutManager = new BorderLayout();
        borderLayout = BorderLayout.CENTER;
        panelName = "Справка_Builder";
        // TODO создание компонента Scroll, return this (для визитора)
        return create("Scroll", visitor);
    }

    @Override
    public String getName() {
        return panelName;
    }

    @Override
    public SwingPanel getPanel(Visitor visitor) {
        return this;
    }

    @Override
    public List<SwingPanel> getComponents() {
        return componentList;
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
    public void setParent(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JComponent getParent() {
        return jPanel;
    }

}
