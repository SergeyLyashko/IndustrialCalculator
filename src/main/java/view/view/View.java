package view.view;

import view.controller.AppComponent;
import view.controller.ComponentsFactory;
import view.controller.MenuReceivable;
import view.controller.Visitor;
import view.model.CalculatorFocusTraversalPolicy;
import view.model.ColorVisitorImpl;
import view.view.*;

import java.util.List;

public class View {

    private final MenuReceivable menuReceivable;

    public View(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    public void createView(){
        Visitor visitor = new ColorVisitorImpl();

        ComponentsFactory calculator = new CalculatorFactory();
        List<AppComponent> calculatorComponents = calculator.createComponents(menuReceivable, visitor);
        AppPanel calculatorPanel = new AppPanel(calculatorComponents, visitor);
        addFocusPolicy(calculatorComponents, calculatorPanel);

        ComponentsFactory settings = new SettingsFactory();
        List<AppComponent> settingsComponents = settings.createComponents(menuReceivable, visitor);
        AppPanel settingsPanel = new AppPanel(settingsComponents, visitor);


        ComponentsFactory info = new InfoFactory();
        List<AppComponent> infoComponents = info.createComponents(menuReceivable, visitor);
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
