package view;

import view.calculator.CalculatorFactory;
import view.info.InfoFactory;
import view.settings.SettingsFactory;
import view.staticelements.AppFrame;
import view.staticelements.AppPanel;

import java.util.List;

public class ViewDispatcher {

    private final MenuReceivable menuReceivable;

    public ViewDispatcher(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    public void createVew(){
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
