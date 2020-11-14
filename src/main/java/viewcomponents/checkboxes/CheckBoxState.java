package viewcomponents.checkboxes;

import viewcomponents.SwingComponent;
import viewcomponents.Visitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CheckBoxState implements ItemListener {

    private final CheckBoxSelectable checkBoxSelectable;
    private final Visitor visitor;

    CheckBoxState(SwingComponent newCheckBox, Visitor visitor) {
        this.checkBoxSelectable = (CheckBoxSelectable) newCheckBox;
        this.visitor = visitor;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                // TODO
                checkBoxSelectable.activate(visitor);
                break;
            case ItemEvent.DESELECTED:
                // TODO
                checkBoxSelectable.deactivate(visitor);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
