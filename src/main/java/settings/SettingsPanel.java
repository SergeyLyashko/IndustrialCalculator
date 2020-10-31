package settings;

import appview.SwingComponent;
import appview.Visitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettingsPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    // TODO del test string
    private static final String PANEL_NAME = "Настройки";

    // TODO del test method
    public String getName() {
        return PANEL_NAME;
    }

    public List<SwingComponent> getComponents(Visitor visitor) {
        List<SwingComponent> componentList = new ArrayList<>();
        SelectableCheckBox theme = getNewCheckBox("theme", visitor);
        SelectableCheckBox toolTip = getNewCheckBox("toolTip", visitor);
        componentList.add(theme);
        componentList.add(toolTip);
        return componentList;
    }
    // TODO вынести в фабрику
    private SelectableCheckBox getNewCheckBox(String type, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SelectableCheckBox createCheckBox(String type) {
                return createNewCheckBox(type);
            }
        };
        return abstractCheckBox.orderedCheckBox(type, visitor);
    }

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
}
