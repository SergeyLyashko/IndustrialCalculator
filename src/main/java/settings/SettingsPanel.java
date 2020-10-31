package settings;

import appview.SwingComponent;
import appview.Visitor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettingsPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Настройки";
    private JComponent componentSwing;

    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }

    private List<SwingComponent> componentList;
    private SelectableCheckBox selectableCheckBox;

    public List<SwingComponent> getComponents(Visitor visitor) {
        visitor.addVisitorComponent(this);
        System.out.println("settings get component");
        componentList = new ArrayList<>();
        addNewCheckBox("theme", visitor);
        addNewCheckBox("toolTip", visitor);
        return componentList;
    }

    private void addNewCheckBox(String type, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SelectableCheckBox createCheckBox(String type) {
                selectableCheckBox = createNewCheckBox(type);
                return selectableCheckBox;
            }
        };
        abstractCheckBox.order(type, visitor);
        JCheckBox componentSwing = abstractCheckBox.getComponentSwing();
        selectableCheckBox.setComponentSwing(componentSwing);
        System.out.println("checkBox: "+selectableCheckBox.getName());
        componentList.add(selectableCheckBox);
    }

    @Override
    public JComponent getSwingComponent() {
        return componentSwing;
    }
/*
    @Override
    public void setComponentSwing(JComponent componentSwing) {
        this.componentSwing = componentSwing;
    }*/

    private SelectableCheckBox createNewCheckBox(String type) throws IllegalStateException {
        switch (type){
            case "theme":
                return new ColorTheme();
            case "toolTip":
                return new ToolTip();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return null;
    }

    @Override
    public String getBorderLayout() {
        return null;
    }


}
