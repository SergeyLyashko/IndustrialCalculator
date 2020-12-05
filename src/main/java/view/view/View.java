package view.view;

import view.MenuListReceiver;
import view.controller.*;
import view.view.calculator.CalculatorFactory;
import view.view.info.InfoFactory;
import view.view.settings.SettingsFactory;

public class View {

    public View(ViewController viewController, MenuListReceiver menuListReceiver) {

        ComponentsFactory calculatorComponents = new CalculatorFactory(viewController, menuListReceiver);
        ComponentsFactory settingsComponents = new SettingsFactory(viewController);
        ComponentsFactory infoComponents = new InfoFactory(viewController);

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, viewController);
        AppPanel settingsPanel = new AppPanel(settingsComponents, viewController);
        AppPanel infoPanel = new AppPanel(infoComponents, viewController);

        AppFrame appFrame = new AppFrame();
        appFrame.addPanel("Калькулятор", calculatorPanel);
        appFrame.addPanel("Настройки", settingsPanel);
        appFrame.addPanel("Справка", infoPanel);

        calculatorPanel.addFocusPolicy(calculatorComponents);
        Visitor visitor = viewController.getVisitor();
        visitor.activate();
    }
}
