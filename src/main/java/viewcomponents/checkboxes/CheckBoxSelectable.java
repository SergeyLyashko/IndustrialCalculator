package viewcomponents.checkboxes;

import viewcomponents.Visitor;

public interface CheckBoxSelectable {

    void activate(Visitor visitor);

    void deactivate(Visitor visitor);

}
