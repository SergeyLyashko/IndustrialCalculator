package settings;

import appview.AbstractCheckBox;
import appview.SelectableCheckBox;
import appview.Visitor;

public class CheckBoxFactory {

    public SelectableCheckBox getCheckBox(String type, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SelectableCheckBox createCheckBox(String type) {
                return create(type);
            }
        };
        return abstractCheckBox.orderedCheckBox(type, visitor);
    }

    private SelectableCheckBox create(String type) throws IllegalStateException {
        switch (type){
            case "theme":
                return new ColorTheme();
            case "toolTip":
                return new ToolTip();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
