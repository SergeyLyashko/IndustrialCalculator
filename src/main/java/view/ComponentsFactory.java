package view;

import java.util.List;

public interface ComponentsFactory {

    List<AppComponent> getComponentList();

    // TODO получатель меню нужен только в 1 фабрике
    void create(MenuReceivable menuReceivable, Visitor visitor);
}
