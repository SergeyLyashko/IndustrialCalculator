package view.view.calculator;

import view.controller.ViewController;
import view.controller.MenuSelectable;
import view.view.AppComponent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DefaultMenuBoxes {

    private final ViewController viewController;
    private List<AppComponent> components;

    DefaultMenuBoxes(ViewController viewController) {
        this.viewController = viewController;
    }

    void createDefaultMenu(MenuSelectable...menus) {
        addListeners(menus);

        Arrays.stream(menus).forEach(element -> {
            viewController.selectMenu(element, "");
        });
        this.components = Stream.of(menus).collect(Collectors.toList());
    }

    List<AppComponent> getComponents(){
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
