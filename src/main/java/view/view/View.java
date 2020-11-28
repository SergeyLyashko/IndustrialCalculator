package view.view;

import view.controller.*;
import view.model.ViewModel;
import view.view.calculator.CalculatorFactory;
import view.view.info.InfoFactory;
import view.view.settings.SettingsFactory;

public class View {

    private final ViewModel viewModel;

    public View(ViewModel viewModel, ViewController viewController) {
        this.viewModel = viewModel;
        createView(viewController);
    }

    private void createView(ViewController viewController){

        ComponentsFactory calculatorComponents = new CalculatorFactory(viewController);
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
