package view.viewframe;

import view.ComponentsFactory;
import view.DataBaseMenuReceiver;
import view.Visitor;
import view.viewcontroller.*;
import view.calculatorcomponents.CalculatorComponentsFactory;
import view.infocomponents.InfoComponentsFactory;
import view.settingscomponents.SettingsComponentsFactory;

public class ViewDispatcherIoC {

    public ViewDispatcherIoC(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver) {

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

        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy();
        calculatorPanel.addFocusPolicy(focusTraversalPolicy);
        Visitor visitor = viewController.getVisitor();
        visitor.activate();
    }
}
