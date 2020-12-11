package view.view;

import view.DataBaseMenuReceiver;
import view.controller.*;
import view.view.calculator.CalculatorComponentsFactory;
import view.view.info.InfoComponentsFactory;
import view.view.settings.SettingsComponentsFactory;

public class ViewDispatcher {

    public ViewDispatcher(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver) {

        ComponentsFactory calculatorComponents = new CalculatorComponentsFactory(viewController, dataBaseMenuReceiver);
        ComponentsFactory settingsComponents = new SettingsComponentsFactory(viewController);
        ComponentsFactory infoComponents = new InfoComponentsFactory(viewController);

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
