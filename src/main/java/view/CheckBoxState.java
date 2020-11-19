package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxState implements ItemListener {

    private final CheckBoxSelectable checkBoxSelectable;
    private final Visitor visitor;

    public CheckBoxState(AppComponent newCheckBox, Visitor visitor) {
        this.checkBoxSelectable = (CheckBoxSelectable) newCheckBox;
        this.visitor = visitor;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                checkBoxSelectable.activate(visitor);
                break;
            case ItemEvent.DESELECTED:
                checkBoxSelectable.deactivate(visitor);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
