package checkboxes;

import appcomponents.SelectableComponent;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CheckBoxState implements ItemListener {

    private final SelectableComponent selectableCheckBox;
    private final Visitor visitor;

    CheckBoxState(SwingComponent newCheckBox, Visitor visitor) {
        this.selectableCheckBox = (SelectableComponent) newCheckBox;
        this.visitor = visitor;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                // TODO
                selectableCheckBox.activate(visitor);
                break;
            case ItemEvent.DESELECTED:
                // TODO
                selectableCheckBox.deactivate(visitor);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
