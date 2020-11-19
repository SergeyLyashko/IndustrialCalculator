package view.calculator;

import view.AppComponent;
import view.MenuReceivable;
import view.calculator.menuboxes.MenuModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuWrapper {

    private final MenuReceivable menuReceivable;
    private List<AppComponent> components;

    public MenuWrapper(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    public void createMenu(MenuSelectable...menus) {
        addListeners(menus);
        Arrays.stream(menus).forEach(element -> element.addReceiver(menuReceivable));
        Arrays.stream(menus).forEach(element -> {
            MenuModel menuModel = new MenuModel(element, "");
            element.setMenuModel(menuModel);
        });
        this.components = Stream.of(menus).collect(Collectors.toList());
    }

    public List<AppComponent> getComponents(){
        return components;
    }

    private void addListeners(MenuSelectable...menus) {
        MenuSelectable assortment = menus[0];
        MenuSelectable types = menus[1];
        MenuSelectable numbers = menus[2];
        assortment.addListener(types);
        assortment.addListener(numbers);
        types.addListener(numbers);
    }
}
