package view.view;

import view.controller.*;
import view.model.ViewModelInterface;
import view.view.calculator.CalculatorFactory;
import view.view.info.InfoFactory;
import view.view.settings.SettingsFactory;

import java.util.List;

public class View {

    private final ViewController viewController;
    private final ViewModelInterface viewModel;

    public View(ViewModelInterface viewModel, ViewController viewController) {
        this.viewModel = viewModel;
        this.viewController = viewController;
    }

    public void createView(){

        Visitor visitor = viewController.getVisitor();

        ComponentsFactory calculator = new CalculatorFactory(viewController);
        List<AppComponent> calculatorComponents = calculator.createComponents();

        AppPanel calculatorPanel = new AppPanel(calculatorComponents, visitor);
        calculatorPanel.addFocusPolicy(calculatorComponents);

        ComponentsFactory settings = new SettingsFactory(viewController);
        List<AppComponent> settingsComponents = settings.createComponents();

        AppPanel settingsPanel = new AppPanel(settingsComponents, visitor);


        ComponentsFactory info = new InfoFactory(viewController);
        List<AppComponent> infoComponents = info.createComponents();

        AppPanel infoPanel = new AppPanel(infoComponents, visitor);

        AppFrame appFrame = new AppFrame();
        appFrame.createPanel("Калькулятор", calculatorPanel);
        appFrame.createPanel("Настройки", settingsPanel);
        appFrame.createPanel("Справка", infoPanel);

        visitor.activate();// TODO решить вопрос с активацией по умолчанию
        appFrame.create();
        appFrame.savePreferencesAndExit();
    }
}
