package checkboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CheckBoxState implements ItemListener {

    private final SelectableCheckBox selectableCheckBox;
    private final Visitor visitor;

    CheckBoxState(SwingComponent newCheckBox, Visitor visitor) {
        this.selectableCheckBox = (SelectableCheckBox) newCheckBox;
        this.visitor = visitor;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                // TODO
                selectableCheckBox.select(visitor);
                break;
            case ItemEvent.DESELECTED:
                // TODO
                selectableCheckBox.deselect(visitor);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
