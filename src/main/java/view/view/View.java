package view.view;

import view.controller.*;
import view.model.CalculatorFocusTraversalPolicy;
import view.model.ColorVisitorImpl;
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

        Visitor visitor = new ColorVisitorImpl();

        ComponentsFactory calculator = new CalculatorFactory();
        List<AppComponent> calculatorComponents = calculator.createComponents(viewController, visitor);
        AppPanel calculatorPanel = new AppPanel(calculatorComponents, visitor);
        addFocusPolicy(calculatorComponents, calculatorPanel);

        ComponentsFactory settings = new SettingsFactory();
        List<AppComponent> settingsComponents = settings.createComponents(viewController, visitor);
        AppPanel settingsPanel = new AppPanel(settingsComponents, visitor);


        ComponentsFactory info = new InfoFactory();
        List<AppComponent> infoComponents = info.createComponents(viewController, visitor);
        AppPanel infoPanel = new AppPanel(infoComponents, visitor);

        AppFrame appFrame = new AppFrame();
        appFrame.createPanel("Калькулятор", calculatorPanel);
        appFrame.createPanel("Настройки", settingsPanel);
        appFrame.createPanel("Справка", infoPanel);

        visitor.activate();
        appFrame.create();
        appFrame.savePreferencesAndExit();
    }

    private void addFocusPolicy(List<AppComponent> componentList, AppPanel panel){
        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy(componentList);
        focusTraversalPolicy.setFocusPolicy(panel);
    }
}
