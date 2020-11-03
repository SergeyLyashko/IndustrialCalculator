package appview;

import appview.SelectableCheckBox;
import appview.Visitor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxState implements ItemListener {

    private final SelectableCheckBox selectableCheckBox;
    private final Visitor visitor;

    public CheckBoxState(SelectableCheckBox newCheckBox, Visitor visitor) {
        this.selectableCheckBox = newCheckBox;
        this.visitor = visitor;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        int stateChange = event.getStateChange();
        switch (stateChange){
            case ItemEvent.SELECTED:
                // TODO
                selectableCheckBox.select();
                visitor.raid();
                break;
            case ItemEvent.DESELECTED:
                // TODO
                selectableCheckBox.deselect();
                visitor.raid();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getStateChange());
        }
    }
}
