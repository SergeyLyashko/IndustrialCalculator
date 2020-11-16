package view;

import java.util.List;

public interface ComponentsFactory {

    List<AppComponent> getComponents();

    void create(MenuReceiver menuReceiver, Visitor visitor);
}
