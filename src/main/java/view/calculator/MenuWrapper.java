package view.calculator;

import view.AppComponent;
import view.ReceivableMenu;
import view.calculator.menuboxes.MenuModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuWrapper {

    private final ReceivableMenu receivableMenu;
    private List<AppComponent> components;

    public MenuWrapper(ReceivableMenu receivableMenu) {
        this.receivableMenu = receivableMenu;
    }

    public void createMenu(SelectableMenu...menus) {
        addListeners(menus);
        Arrays.stream(menus).forEach(element -> element.addReceiver(receivableMenu));
        Arrays.stream(menus).forEach(element -> new MenuModel(element, ""));
        this.components = Stream.of(menus).collect(Collectors.toList());
    }

    public List<AppComponent> getComponents(){
        return components;
    }

    private void addListeners(SelectableMenu...menus) {
        SelectableMenu assortment = menus[0];
        SelectableMenu types = menus[1];
        SelectableMenu numbers = menus[2];
        assortment.addListener(types);
        assortment.addListener(numbers);
        types.addListener(numbers);
    }
}
