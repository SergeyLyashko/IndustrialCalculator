package view;

import view.Visitor;

public interface CheckBoxSelectable {

    void activate(Visitor visitor);

    void deactivate(Visitor visitor);

}
