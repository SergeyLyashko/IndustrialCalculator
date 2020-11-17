package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxState implements ItemListener {

    private final CheckBoxSelectable checkBoxSelectable;

    public CheckBoxState(AppComponent newCheckBox) {
        this.checkBoxSelectable = (CheckBoxSelectable) newCheckBox;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                checkBoxSelectable.activate();
                break;
            case ItemEvent.DESELECTED:
                checkBoxSelectable.deactivate();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
