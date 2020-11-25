package view.view;

import view.controller.ViewController;
import view.view.AppComponent;
import view.controller.MenuReceivable;
import view.controller.MenuSelectable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultMenu {

    private final ViewController viewController;
    private List<AppComponent> components;

    public DefaultMenu(ViewController viewController) {
        this.viewController = viewController;
    }

    public void createDefaultMenu(MenuSelectable...menus) {
        addListeners(menus);
        //Arrays.stream(menus).forEach(element -> element.addReceiver(menuReceivable));

        Arrays.stream(menus).forEach(element -> {
            //MenuModel menuModel = new MenuModel(element, "");
            //element.setMenuModel(menuModel);
            viewController.selectMenu(element, "");
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
        assortment.addMenuListener(types);
        assortment.addMenuListener(numbers);
        types.addMenuListener(numbers);
    }
}
