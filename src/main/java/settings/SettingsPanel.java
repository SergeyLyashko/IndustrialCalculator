package settings;

import appview.SelectableCheckBox;
import appview.SwingPanel;
import appview.Visitor;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettingsPanel implements Serializable, SwingPanel {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Настройки";
    private JPanel jPanel;
    private List<SwingPanel> componentList;

    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }

    public SwingPanel getPanel(Visitor visitor) {
        componentList = new ArrayList<>();
        CheckBoxFactory checkBoxFactory = new CheckBoxFactory();
        SelectableCheckBox theme = checkBoxFactory.getCheckBox("theme", visitor);
        SelectableCheckBox toolTip = checkBoxFactory.getCheckBox("toolTip", visitor);
        componentList.add(theme);
        componentList.add(toolTip);
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
    public void setParentComponent(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JComponent getParentsComponent() {
        return jPanel;
    }
}
