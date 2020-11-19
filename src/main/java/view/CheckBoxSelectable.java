package view;

public interface CheckBoxSelectable {

    default void activate(Visitor visitor){}

    default void deactivate(Visitor visitor){}

}
