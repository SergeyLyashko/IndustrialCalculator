package view;

import view.calculator.CalculatorFactory;
import view.info.InfoFactory;
import view.settings.SettingsFactory;
import view.staticelements.AppFrame;

public class ViewDispatcher {

    private final ReceivableMenu receivableMenu;

    public ViewDispatcher(ReceivableMenu receivableMenu) {
        this.receivableMenu = receivableMenu;
    }

    public void createVew(){
        Visitor visitor = new VisitorImpl();

        ComponentsFactory calculator = new CalculatorFactory();
        calculator.create(receivableMenu, visitor);

        ComponentsFactory settings = new SettingsFactory();
        settings.create(receivableMenu, visitor);

        ComponentsFactory info = new InfoFactory();
        info.create(receivableMenu, visitor);

        AppFrame appFrame = new AppFrame();
        appFrame.createPanel("Калькулятор", calculator, visitor);
        appFrame.createPanel("Настройки", settings, visitor);
        appFrame.createPanel("Справка", info, visitor);

        appFrame.create();
        appFrame.savePreferencesAndExit();
    }

}
