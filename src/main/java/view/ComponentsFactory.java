package view;

import java.util.List;

public interface ComponentsFactory {

    // TODO получатель меню нужен только в 1 фабрике
    List<AppComponent> createComponents(MenuReceivable menuReceivable, Visitor visitor);
}
