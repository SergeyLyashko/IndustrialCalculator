package checkboxes;

import appcomponents.Visitor;

public interface CheckBoxSelectable {

    void activate(Visitor visitor);

    void deactivate(Visitor visitor);

}
