package appcomponents;

import fields.AbstractField;
import fields.SelectableField;

public class FieldFactory {

    private SelectableField selectableField;

    public void createNewField(SelectableField selectableField){
        this.selectableField = create(selectableField);
    }

    private SelectableField create(SelectableField selectableField) {
        AbstractField abstractField = new AbstractField() {
            @Override
            public SelectableField create() {
                return selectableField;
            }
        };
        return abstractField.orderedField();
    }

    public SelectableField getComponent(){
        return selectableField;
    }
}
